package org.stop_lang.validation;

import org.antlr.symtab.FieldSymbol;
import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RefPhase extends StopBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();

    public RefPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        this.globals = globals;
    }

    @Override public void enterFile(StopParser.FileContext ctx) {
        currentScope = globals;
    }

    @Override public void enterModel(StopParser.ModelContext ctx) {
        currentScope = scopes.get(ctx);
    }

    @Override public void exitModel(StopParser.ModelContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void exitField(StopParser.FieldContext ctx) {
        String name = ctx.ID().getSymbol().getText();
        Symbol symbol = currentScope.resolve(name);
        if (symbol instanceof ModelFieldSymbol){
            ModelFieldSymbol modelFieldSymbol = (ModelFieldSymbol)symbol;
            String modelName = modelFieldSymbol.getTypeName();
            Symbol modelSymbol = globals.resolve(modelName);
            Symbol enumSymbol = currentScope.resolve(modelName);
            if(modelSymbol == null){
                if ((enumSymbol != null) && (enumSymbol instanceof EnumSymbol)){
                    // Found symbol
                } else {
                    errors.add(new StopValidationException("Couldn't define field \""+
                            name +"\" because " + modelName + " isn't defined"));
                }
            }
        }
        if (symbol instanceof CollectionFieldSymbol){
            CollectionFieldSymbol collectionFieldSymbol = (CollectionFieldSymbol)symbol;
            String modelName = collectionFieldSymbol.getTypeName();
            Symbol modelSymbol = globals.resolve(modelName);
            Symbol enumSymbol = currentScope.resolve(modelName);
            if (collectionFieldSymbol.isState() && (modelSymbol == null)) {
                if ((enumSymbol != null) && (enumSymbol instanceof EnumSymbol)) {
                    // Found symbol
                } else {
                    errors.add(new StopValidationException("Couldn't define collection field \"" +
                            name + "\" because " + modelName + " isn't defined"));
                }
            }
        }
        if (ctx.async_source() != null){
            String modelName = ctx.async_source().MODEL_TYPE().getText();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define field \""+
                        name +"\" because " + modelName + " isn't defined"));
            }else{
                ModelSymbol theModelSymbol = (ModelSymbol)modelSymbol;
                String typeString = null;
                if (ctx.type()!=null){
                    typeString = ctx.type().getText();
                }
                if (ctx.collection()!=null){
                    typeString = ctx.collection().getText();
                }
                if(typeString!=null){
                    if (theModelSymbol.getReturnTypeString()!=null){
                        if (!typeString.equals(theModelSymbol.getReturnTypeString())){
                            errors.add(new StopValidationException("Couldn't define field \""+
                                    name +"\" because "
                                    + typeString + " doesn't match async return type of "
                                    + theModelSymbol.getReturnTypeString()));
                            System.err.println();
                        }
                    }
                }

                for( Symbol modelSymbolSymbol : theModelSymbol.getAllSymbols()){
                    if (modelSymbolSymbol instanceof StopFieldSymbol){
                        StopFieldSymbol fieldSymbol = (StopFieldSymbol)modelSymbolSymbol;
                        String fieldName = fieldSymbol.getName();

                        if (symbol instanceof StopFieldSymbol) {
                            Map<String, String> asyncSourceMapping = ((StopFieldSymbol)symbol).getAsyncMapping();
                            if (asyncSourceMapping != null) {
                                for (Map.Entry<String, String> mappingEntry : asyncSourceMapping.entrySet()) {
                                    if (mappingEntry.getKey().equalsIgnoreCase(fieldName)) {
                                        fieldName = mappingEntry.getValue();
                                    }
                                }
                            }
                        }

                        Symbol currentScopeProperty = currentScope.resolve(fieldName);
                        if ((currentScopeProperty!= null) && (currentScopeProperty instanceof StopFieldSymbol)){
                            StopFieldSymbol currentScopeFieldSymbol = (StopFieldSymbol)currentScopeProperty;

                            if (!currentScopeFieldSymbol.getTypeName().equals(fieldSymbol.getTypeName())){
                                errors.add(new StopValidationException("Couldn't define field \""+
                                        name +"\" because "
                                        + fieldSymbol.getName() + " mapping to "+fieldName+" doesn't match required type "
                                        + fieldSymbol.getTypeName()));
                                System.err.println();
                            }
                        }else if (!fieldSymbol.isOptional()){
                            errors.add(new StopValidationException("Couldn't define field \""+
                                    name +"\" because "
                                    + fieldSymbol.getName() + " cannot be mapped within "
                                    + currentScope.getName()));
                            System.err.println();
                        }
                    }
                }
            }
        }
    }

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        Symbol modelSymbol = globals.resolve(modelName);
        if(modelSymbol == null){
            errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        Symbol modelSymbol = globals.resolve(modelName);
        if(modelSymbol == null){
            errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitReturn_type(StopParser.Return_typeContext ctx) {
        if (ctx.type()!=null){
            if(ctx.type().model_type() != null){
                String modelName = ctx.type().model_type().MODEL_TYPE().getText();
                Symbol modelSymbol = globals.resolve(modelName);
                if(modelSymbol == null){
                    errors.add(new StopValidationException("Couldn't define return type because " + modelName + " isn't defined"));
                }
            }
        }
        if (ctx.collection() != null){
            if (ctx.collection().type().model_type() != null){
                String modelName = ctx.collection().type().model_type().MODEL_TYPE().getText();
                Symbol modelSymbol = globals.resolve(modelName);
                if(modelSymbol == null){
                    errors.add(new StopValidationException("Couldn't define return collection because "
                            + modelName + " isn't defined"));
                }
            }
        }
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        if (ctx.MODEL_TYPE()!=null){
            String modelName = ctx.MODEL_TYPE().getText();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define thrown transition because "
                        + modelName + " isn't defined"));
            }
        }
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx) {
        if (ctx.transition().MODEL_TYPE()!=null){
            String modelName = ctx.transition().MODEL_TYPE().getText();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define timeout transition because "
                        + modelName + " isn't defined"));
            }
            if (modelSymbol instanceof ModelSymbol) {
                ModelSymbol theModelSymbol = (ModelSymbol) modelSymbol;
                Symbol timedOutStateSymbol = theModelSymbol.resolve("timedOutState");
                if (timedOutStateSymbol != null){
                    if (timedOutStateSymbol instanceof ModelFieldSymbol){
                        ModelFieldSymbol fieldSymbol = (ModelFieldSymbol)timedOutStateSymbol;
                        if (!fieldSymbol.getTypeName().equals(currentScope.getName())){
                            errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState has type " + fieldSymbol.getTypeName() + " instead of " + currentScope.getName()));
                        }
                    }else {
                        errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState isn't defined"));
                    }
                }else{
                    errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState not defined"));
                }
            }
        }
    }
}
