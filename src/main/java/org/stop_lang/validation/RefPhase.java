package org.stop_lang.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RefPhase extends StopBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();
    private String packageName = null;

    public RefPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        this.globals = globals;
    }

    @Override public void exitPackageDeclaration(StopParser.PackageDeclarationContext ctx) {
        this.packageName = ctx.packageName().getText();
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
        StopFieldSymbol symbol = (StopFieldSymbol)currentScope.resolve(name);
        DynamicModelSymbol dynamicModel = null;
        boolean isCollection = false;

        if (symbol instanceof ModelFieldSymbol){
            ModelFieldSymbol modelFieldSymbol = (ModelFieldSymbol)symbol;
            String modelName = modelFieldSymbol.getTypeName();
            Symbol modelSymbol = globals.resolve(modelName);
            Symbol enumSymbol = currentScope.resolve(modelFieldSymbol.getSimpleTypeName());
            if(modelSymbol == null){
                if ((enumSymbol != null) && (enumSymbol instanceof EnumSymbol)){
                    // Found symbol
                } else {
                    errors.add(new StopValidationException("Couldn't define field \""+
                            name +"\" because " + modelName + " isn't defined"));
                }
            }else{
                dynamicModel = modelFieldSymbol.getDynamicModel();
            }
        }
        if (symbol instanceof CollectionFieldSymbol){
            CollectionFieldSymbol collectionFieldSymbol = (CollectionFieldSymbol)symbol;
            String modelName = collectionFieldSymbol.getTypeName();
            Symbol modelSymbol = globals.resolve(modelName);
            Symbol enumSymbol = currentScope.resolve(collectionFieldSymbol.getSimpleTypeName());
            if (collectionFieldSymbol.isState() && (modelSymbol == null)) {
                if ((enumSymbol != null) && (enumSymbol instanceof EnumSymbol)) {
                    // Found symbol
                } else {
                    errors.add(new StopValidationException("Couldn't define collection field \"" +
                            name + "\" because " + modelName + " isn't defined"));
                }
            }else{
                dynamicModel = collectionFieldSymbol.getDynamicModel();
            }
            isCollection = true;
        }
        if (dynamicModel != null){
            String modelName = dynamicModel.getFullName();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define field \""+
                        name +"\" because " + modelName + " isn't defined"));
            }else{
                ModelSymbol dynamicModelSymbol = (ModelSymbol)modelSymbol;
                ReturnSymbol dynamicModelReturnSymbol = dynamicModelSymbol.getReturn();

                if (dynamicModelReturnSymbol == null){
                    errors.add(new StopValidationException("Couldn't define field \""+
                            name +"\" because " + modelName + " doesn't define a return type"));
                }else{
                    if (isCollection != dynamicModelReturnSymbol.isCollection()){
                        String explanation = "is not a collection";
                        if (dynamicModelReturnSymbol.isCollection()){
                            explanation = "is a collection";
                        }
                        errors.add(new StopValidationException("Couldn't define field \""+
                                name +"\" because " + modelName + " return type "+explanation));
                    }else{
                        if ((symbol instanceof ScalarFieldSymbol) && !dynamicModelReturnSymbol.isScalar()){
                            errors.add(new StopValidationException("Couldn't define field \""+
                                    name +"\" because " + modelName + " doesn't return a scalar type of "+symbol.getTypeName()));
                        }
                        if ((symbol instanceof ModelFieldSymbol) && dynamicModelReturnSymbol.isScalar()){
                            errors.add(new StopValidationException("Couldn't define field \""+
                                    name +"\" because " + modelName + " doesn't return type of "+symbol.getTypeName()));
                        }
                        if (!symbol.getTypeName().equals(dynamicModelReturnSymbol.getName())){
                            errors.add(new StopValidationException("Couldn't define field \""+
                                    name +"\" because " + symbol.getTypeName() + " doesn't return type of "+dynamicModelReturnSymbol.getName()));
                        }
                    }
                }

                for( Symbol dynamicModelFieldSymbol : dynamicModelSymbol.getAllSymbols()){
                    if (dynamicModelFieldSymbol instanceof StopFieldSymbol){
                        StopFieldSymbol fieldSymbol = (StopFieldSymbol)dynamicModelFieldSymbol;
                        String fieldName = fieldSymbol.getName();

                        if (symbol.getDynamicModel()!=null) {
                            Map<String, String> asyncSourceMapping = symbol.getDynamicModel().getSourceMapping();
                            if (asyncSourceMapping != null) {
                                for (Map.Entry<String, String> mappingEntry : asyncSourceMapping.entrySet()) {
                                    if (mappingEntry.getKey().equalsIgnoreCase(fieldName)) {
                                        fieldName = mappingEntry.getValue();
                                        break;
                                    }
                                }
                            }
                        }

                        Symbol currentScopeProperty = findReference(currentScope, fieldName, fieldSymbol.isOptional());
                        if ((currentScopeProperty!= null) && (currentScopeProperty instanceof StopFieldSymbol)){
                            StopFieldSymbol currentScopeFieldSymbol = (StopFieldSymbol)currentScopeProperty;

                            String fieldSymbolTypeName = fieldSymbol.getTypeName();

                            if (!currentScopeFieldSymbol.getTypeName().equals(fieldSymbolTypeName)){
                                errors.add(new StopValidationException("Couldn't define field \""+
                                        name +"\" because "
                                        + fieldSymbol.getName() + " mapping to "+fieldName+" doesn't match required type "
                                        + fieldSymbol.getTypeName()));
                                System.err.println();
                            }

                            if (symbol instanceof  StopFieldSymbol) {
                                if (currentScopeFieldSymbol.isOptional()
                                        && !fieldSymbol.isOptional()
                                        && !((StopFieldSymbol) symbol).isOptional()) {
                                    errors.add(new StopValidationException("Couldn't define field \""+
                                            name +"\" because "
                                            + fieldName + " is optional and "+symbol.getName()+" is not optional"));
                                    System.err.println();
                                }
                            }
                        }else if ((fieldSymbol.getDynamicModel()==null) && !fieldSymbol.isOptional()){
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
        TransitionSymbol transitionSymbol = ((ModelSymbol)currentScope).getTransition(ctx);
        if(transitionSymbol == null){
            errors.add(new StopValidationException("Couldn't define transition because it was not defined"));
        }else {
            String modelName = transitionSymbol.getName();
            Symbol modelSymbol = globals.resolve(modelName);
            if (modelSymbol == null) {
                errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
            }else {
                ModelSymbol transitionModelSymbol = (ModelSymbol)modelSymbol;
                if (transitionModelSymbol.isQueue()){
                    errors.add(new StopValidationException("Couldn't define transition because " + modelName + " is a queue state"));
                }
            }
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        EnqueueSymbol enqueueSymbol = ((ModelSymbol)currentScope).getEnqueue(ctx);
        if(enqueueSymbol == null){
            errors.add(new StopValidationException("Couldn't define enqueue because it was not defined"));
        }else {
            String modelName = enqueueSymbol.getName();
            Symbol modelSymbol = globals.resolve(modelName);
            if (modelSymbol == null) {
                errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't defined"));
            }else {
                ModelSymbol enqueueModelSymbol = (ModelSymbol)modelSymbol;
                if (!enqueueModelSymbol.isQueue()){
                    errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't a queue state"));
                }
            }
        }
    }

    @Override public void exitReturn_type(StopParser.Return_typeContext ctx) {
        ReturnSymbol returnSymbol = ((ModelSymbol)currentScope).getReturn();
        if(returnSymbol == null){
            errors.add(new StopValidationException("Couldn't define return type because it was not defined"));
        }else {
            if (!returnSymbol.isScalar()){
                String modelName = returnSymbol.getName();
                Symbol modelSymbol = globals.resolve(modelName);

                if (modelSymbol == null) {
                    errors.add(new StopValidationException("Couldn't define return type because " + modelName + " isn't defined"));
                }
            }
        }
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        ThrowSymbol throwSymbol = ((ModelSymbol)currentScope).getError(ctx);
        if(throwSymbol == null){
            errors.add(new StopValidationException("Couldn't define error because it was not defined"));
        }else {
            String modelName = throwSymbol.getName();
            Symbol modelSymbol = globals.resolve(modelName);
            if (modelSymbol == null) {
                errors.add(new StopValidationException("Couldn't define thrown transition because "
                        + modelName + " isn't defined"));
            }else {
                ModelSymbol throwModelSymbol = (ModelSymbol)modelSymbol;
                if (throwModelSymbol.isQueue()){
                    errors.add(new StopValidationException("Couldn't define transition because " + modelName + " is a queue state"));
                }
            }
        }
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx) {
        TimeoutSymbol timeoutSymbol = ((ModelSymbol)currentScope).getTimeout();
        if(timeoutSymbol == null){
            errors.add(new StopValidationException("Couldn't define timeout because it was not defined"));
        }else {
            String modelName = timeoutSymbol.getFullName();
            Symbol modelSymbol = globals.resolve(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define timeout transition because "
                        + modelName + " isn't defined"));
            }else{
                ModelSymbol timeoutModelSymbol = (ModelSymbol)modelSymbol;
                if (timeoutModelSymbol.isQueue()){
                    errors.add(new StopValidationException("Couldn't define timeout transition because " + modelName + " is a queue state"));
                }
            }
            if (modelSymbol instanceof ModelSymbol) {
                ModelSymbol timeoutModelSymbol = (ModelSymbol) modelSymbol;

                int fieldCount = 0;

                for(Symbol timeoutModelFieldSymbol : timeoutModelSymbol.getAllSymbols()){
                    if (timeoutModelFieldSymbol instanceof StopFieldSymbol){
                        fieldCount++;
                    }
                }

                if (fieldCount > 0) {
                    if(fieldCount == 1) {
                        Symbol timedOutStateSymbol = timeoutModelSymbol.resolve("timedOutState");
                        if (timedOutStateSymbol != null) {
                            if (timedOutStateSymbol instanceof ModelFieldSymbol) {
                                ModelFieldSymbol fieldSymbol = (ModelFieldSymbol) timedOutStateSymbol;
                                String fieldSymbolTypeName = fieldSymbol.getTypeName();
                                if (!fieldSymbolTypeName.equals(currentScope.getName())) {
                                    errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState has type " + fieldSymbol.getTypeName() + " instead of " + currentScope.getName()));
                                }
                            } else {
                                errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState isn't defined as type " + currentScope.getName()));
                            }
                        } else {
                            errors.add(new StopValidationException("Couldn't define timeout transition because timedOutState not defined"));
                        }
                    }else{
                        errors.add(new StopValidationException("Couldn't define timeout transition because only the timedOutState property can be defined"));
                    }
                }
            }
        }
    }

    private Symbol findReference(Scope scope, String reference, boolean optional){
        String[] parts = reference.split("\\.");
        String valueName = parts[0];

        Symbol symbol = scope.resolve(valueName);

        if (symbol!=null){
            if (parts.length > 1){
                if (symbol instanceof ModelFieldSymbol) {
                    ModelFieldSymbol modelFieldSymbol = (ModelFieldSymbol) symbol;
                    String modelName = modelFieldSymbol.getTypeName();
                    ModelSymbol modelSymbol = (ModelSymbol)globals.resolve(modelName);
                    if (modelSymbol!=null) {
                        if (optional || (optional == modelFieldSymbol.isOptional())) {
                            List<String> newParts = new ArrayList<>();
                            for (int i = 1; i < parts.length; i++) {
                                newParts.add(parts[i]);
                            }
                            String newReference = String.join(".", newParts);
                            return findReference(modelSymbol, newReference, optional);
                        }
                    }
                }
            }else {
                return symbol;
            }
        }

        return null;
    }
}
