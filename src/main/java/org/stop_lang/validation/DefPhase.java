package org.stop_lang.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefPhase extends StopBaseListener {
    public ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
    public GlobalScope globals;
    public Scope currentScope;
    public List<Exception> errors = new ArrayList<Exception>();
    private String packageName;

    @Override public void enterFile(StopParser.FileContext ctx) {
        globals = new GlobalScope(null);
        currentScope = globals;
    }

    @Override public void exitPackageDeclaration(StopParser.PackageDeclarationContext ctx) {
        packageName = ctx.packageName().getText();
    }

    @Override public void exitFile(StopParser.FileContext ctx) {

    }

    @Override public void enterModel(StopParser.ModelContext ctx) {
        ModelSymbol modelSymbol = new ModelSymbol(ctx, currentScope, packageName);
        currentScope.define(modelSymbol);
        saveScope(ctx, modelSymbol);
        currentScope = modelSymbol;
    }

    @Override public void exitModel(StopParser.ModelContext ctx) {
        ModelSymbol modelSymbol = (ModelSymbol) currentScope;
        if (modelSymbol.getAsync() && (modelSymbol.getTimeout() == 0)){
            errors.add(new StopValidationException("Asynchronous states " + modelSymbol.getName() + " must have a timeout defined"));
        }
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx){
        ModelSymbol modelSymbol = (ModelSymbol) currentScope;
        String numberString = ctx.NUMBER().getText();
        String modelTransitionName = getFullModelName(ctx.transition().MODEL_TYPE().getText());
        modelSymbol.setTimeoutTransition(modelTransitionName);
        int timeout = Integer.parseInt(numberString);
        if(timeout == 0){
            errors.add(new StopValidationException("Timeout must be greater than zero for " + modelSymbol.getName()));
        }else {
            modelSymbol.setTimeout(timeout);
        }
    }

    @Override public void enterEnumeration(StopParser.EnumerationContext ctx) {
        EnumSymbol enumSymbol = new EnumSymbol(ctx, currentScope);
        currentScope.define(enumSymbol);
        saveScope(ctx, enumSymbol);
        currentScope = enumSymbol;
    }

    @Override public void exitEnumeration(StopParser.EnumerationContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void exitEnum_value(StopParser.Enum_valueContext ctx) {
        String enumValue = ctx.MODEL_TYPE().getText();
        if (currentScope instanceof EnumSymbol){
            EnumSymbol enumSymbol = (EnumSymbol)currentScope;
            enumSymbol.addValue(enumValue);
        }
    }

    @Override public void exitField(StopParser.FieldContext ctx) {
        String fieldName = ctx.ID().getText();
        StopFieldSymbol field = null;

        String packageNameForField=null;

        ParseTree p = ctx.getParent().getParent().getParent().getParent().getChild(0);
        if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
            StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
            packageNameForField = decl.packageName().getText();
        }

        if (ctx.type() != null && ctx.type().model_type() != null) {
            String modelName = ctx.type().model_type().getText();
            field = new ModelFieldSymbol(fieldName, modelName, packageNameForField);
        }else if (ctx.type()!=null && ctx.type().scalar_type() != null){
            String typeName = ctx.type().scalar_type().getText();
            field = new ScalarFieldSymbol(fieldName, typeName, packageNameForField);
        }else if (ctx.collection() != null && ctx.collection().type() != null){
            String typeName = ctx.collection().type().getText();
            boolean isState = ctx.collection().type().model_type() != null;
            field = new CollectionFieldSymbol(fieldName, typeName, isState, packageNameForField);
        }
        if(field != null){
            if (ctx.async_source() != null){
                String asyncModel = getFullModelName(ctx.async_source().model_type().getText());
                field.setAsyncSource(asyncModel);

                if (ctx.async_source().async_source_mapping() != null){
                    Map<String, String> asyncSourceMapping = new HashMap<String, String>();
                    for (StopParser.Async_source_mapping_parameterContext parameterContext :
                            ctx.async_source().async_source_mapping().async_source_mapping_parameter()){
                        asyncSourceMapping.put(parameterContext.ID().getText(), parameterContext.async_source_mapping_parameter_rename().getText());
                    }
                    field.setAsyncMapping(asyncSourceMapping);
                }
            }
            if ( ctx.OPTIONAL() != null){
                field.setOptional(true);
            }
            currentScope.define(field);
        }
    }

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        String modelName = getFullModelName(ctx.MODEL_TYPE().getText());
        TransitionSymbol transitionSymbol = new TransitionSymbol(modelName, currentScope);
        currentScope.define(transitionSymbol);
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        String modelName = getFullModelName(ctx.MODEL_TYPE().getText());
        EnqueueSymbol enqueueSymbol = new EnqueueSymbol(modelName, currentScope);
        currentScope.define(enqueueSymbol);
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        if (currentScope instanceof  ModelSymbol){
            ModelSymbol modelSymbol = (ModelSymbol)currentScope;
            String modelName = ctx.MODEL_TYPE().getText();
            modelSymbol.addErrorType(getFullModelName(modelName));
        }
    }

    void saveScope(ParserRuleContext ctx, Scope s){
        scopes.put(ctx, s);
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
