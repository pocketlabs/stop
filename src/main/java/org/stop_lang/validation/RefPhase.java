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
        Symbol symbol = currentScope.resolve(name);
        if (symbol instanceof ModelFieldSymbol){
            ModelFieldSymbol modelFieldSymbol = (ModelFieldSymbol)symbol;
            String modelName = modelFieldSymbol.getFullTypeName();
            Symbol modelSymbol = globalsResolveWithPackage(modelName);
            Symbol enumSymbol = currentScope.resolve(modelFieldSymbol.getTypeName());
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
            String modelName = collectionFieldSymbol.getFullTypeName();
            Symbol modelSymbol = globalsResolveWithPackage(modelName);
            Symbol enumSymbol = currentScope.resolve(collectionFieldSymbol.getTypeName());
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
            String modelName = ctx.async_source().model_type().getText();
            Symbol modelSymbol = globalsResolveWithPackage(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define field \""+
                        name +"\" because " + modelName + " isn't defined"));
            }else{
                ModelSymbol theModelSymbol = (ModelSymbol)modelSymbol;
                String typeString = null;
                boolean isCollection = false;

                if (ctx.type()!=null){
                    typeString = getFullModelName(ctx.type().getText());
                }else if (ctx.collection()!=null){
                    isCollection = true;
                    typeString = getFullModelName(ctx.collection().type().getText());
                }
                if ((typeString!= null) && !(typeString.equals(theModelSymbol.getReturnType()) && (theModelSymbol.isReturnCollection()==isCollection))){
                    errors.add(new StopValidationException("Couldn't define field \""+
                            name +"\" because "
                            + typeString + " doesn't match async return type of "
                            + theModelSymbol.getReturnType()));
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
                                        break;
                                    }
                                }
                            }
                        }

                        Symbol currentScopeProperty = findReference(currentScope, fieldName, fieldSymbol.isOptional());
                        if ((currentScopeProperty!= null) && (currentScopeProperty instanceof StopFieldSymbol)){
                            StopFieldSymbol currentScopeFieldSymbol = (StopFieldSymbol)currentScopeProperty;

                            String fieldSymbolTypeName = fieldSymbol.getFullTypeName();

                            if (!currentScopeFieldSymbol.getFullTypeName().equals(fieldSymbolTypeName)){
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
                        }else if ((fieldSymbol.getAsyncSource()==null) && !fieldSymbol.isOptional()){
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
        Symbol modelSymbol = globalsResolveWithPackage(modelName);
        if(modelSymbol == null){
            errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        Symbol modelSymbol = globalsResolveWithPackage(modelName);
        if(modelSymbol == null){
            errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitReturn_type(StopParser.Return_typeContext ctx) {
        if (ctx.type()!=null){
            if(ctx.type().model_type() != null){
                String modelName = ctx.type().model_type().MODEL_TYPE().getText();

                ParseTree p = ctx.getParent().getParent().getChild(0);
                if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
                    StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
                    String packageName = decl.packageName().getText();
                    modelName = packageName + "." + modelName;
                }

                Symbol modelSymbol = globalsResolveWithPackage(modelName);

                if(modelSymbol == null){
                    errors.add(new StopValidationException("Couldn't define return type because " + modelName + " isn't defined"));
                }
            }
        }
        if (ctx.collection() != null){
            if (ctx.collection().type().model_type() != null){
                String modelName = ctx.collection().type().model_type().MODEL_TYPE().getText();

                ParseTree p = ctx.getParent().getParent().getChild(0);
                if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
                    StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
                    String packageName = decl.packageName().getText();
                    modelName = packageName + "." + modelName;
                }

                Symbol modelSymbol = globalsResolveWithPackage(modelName);
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
            Symbol modelSymbol = globalsResolveWithPackage(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define thrown transition because "
                        + modelName + " isn't defined"));
            }
        }
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx) {
        if (ctx.transition().MODEL_TYPE()!=null){
            String modelName = ctx.transition().MODEL_TYPE().getText();
            Symbol modelSymbol = globalsResolveWithPackage(modelName);
            if(modelSymbol == null){
                errors.add(new StopValidationException("Couldn't define timeout transition because "
                        + modelName + " isn't defined"));
            }
            if (modelSymbol instanceof ModelSymbol) {
                ModelSymbol theModelSymbol = (ModelSymbol) modelSymbol;

                int fieldCount = 0;

                for(Symbol theModelSymbolSymbol : theModelSymbol.getAllSymbols()){
                    if (theModelSymbolSymbol instanceof StopFieldSymbol){
                        fieldCount++;
                    }
                }

                if (fieldCount > 0) {
                    if(fieldCount == 1) {
                        Symbol timedOutStateSymbol = theModelSymbol.resolve("timedOutState");
                        if (timedOutStateSymbol != null) {
                            if (timedOutStateSymbol instanceof ModelFieldSymbol) {
                                ModelFieldSymbol fieldSymbol = (ModelFieldSymbol) timedOutStateSymbol;
                                String fieldSymbolTypeName = fieldSymbol.getTypeName();
                                if ((packageName!=null) && !fieldSymbolTypeName.contains(".")){
                                    fieldSymbolTypeName = packageName + "." + fieldSymbol.getTypeName();
                                }
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
                    ModelSymbol modelSymbol = (ModelSymbol)globalsResolveWithPackage(modelName);
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

    private Symbol globalsResolveWithPackage(String name){
        return globals.resolve(getFullModelName(name));
    }

    private String getFullModelName(String name){
        if (!isReference(name) && (packageName!=null)){
            return packageName + "." + name;
        }
        return name;
    }

    private boolean isReference(String name){
        return name.contains(".");
    }
}
