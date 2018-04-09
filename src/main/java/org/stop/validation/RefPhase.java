package org.stop.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop.parser.StopBaseListener;
import org.stop.parser.StopParser;
import org.stop.symbols.CollectionFieldSymbol;
import org.stop.symbols.EnumSymbol;
import org.stop.symbols.ModelFieldSymbol;
import org.stop.symbols.ModelSymbol;

import java.util.ArrayList;
import java.util.List;

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

    @Override public void enterBlock(StopParser.BlockContext ctx) {
        currentScope = scopes.get(ctx);
    }

    @Override public void exitBlock(StopParser.BlockContext ctx) {
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
                    errors.add(new ValidationException("Couldn't define field \""+
                            name +"\" because " + modelName + " isn't defined"));
                }
            }
        }
        if (symbol instanceof CollectionFieldSymbol){
            CollectionFieldSymbol collectionFieldSymbol = (CollectionFieldSymbol)symbol;
            String modelName = collectionFieldSymbol.getTypeName();
            Symbol modelSymbol = globals.resolve(modelName);
            Symbol enumSymbol = currentScope.resolve(modelName);
            if(modelSymbol == null){
                if ((enumSymbol != null) && (enumSymbol instanceof  EnumSymbol)){
                    // Found symbol
                } else {
                    errors.add(new ValidationException("Couldn't define collection field \""+
                            name +"\" because " + modelName + " isn't defined"));
                }
            }
        }
        if (ctx.async_source() != null){
            String modelName = ctx.async_source().MODEL_TYPE().getText();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new ValidationException("Couldn't define field \""+
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
                    if (theModelSymbol.getAsyncReturnType()!=null){
                        if (!typeString.equals(theModelSymbol.getAsyncReturnType())){
                            errors.add(new ValidationException("Couldn't define field \""+
                                    name +"\" because "
                                    + typeString + " doesn't match async return type of "
                                    + theModelSymbol.getAsyncReturnType()));
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
            errors.add(new ValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitReturn_type(StopParser.Return_typeContext ctx) {
        if (ctx.type()!=null){
            if(ctx.type().model_type() != null){
                String modelName = ctx.type().model_type().MODEL_TYPE().getText();
                Symbol modelSymbol = globals.resolve(modelName);
                if(modelSymbol == null){
                    errors.add(new ValidationException("Couldn't define return type because " + modelName + " isn't defined"));
                }
            }
        }
        if (ctx.collection() != null){
            if (ctx.collection().type().model_type() != null){
                String modelName = ctx.collection().type().model_type().MODEL_TYPE().getText();
                Symbol modelSymbol = globals.resolve(modelName);
                if(modelSymbol == null){
                    errors.add(new ValidationException("Couldn't define return collection because "
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
                errors.add(new ValidationException("Couldn't define thrown transition because "
                        + modelName + " isn't defined"));
            }
        }
    }
}
