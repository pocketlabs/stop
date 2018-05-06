package org.stop_lang.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.*;

import java.util.ArrayList;
import java.util.List;

public class DefPhase extends StopBaseListener {
    public ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
    public GlobalScope globals;
    public Scope currentScope;
    public List<Exception> errors = new ArrayList<Exception>();

    @Override public void enterFile(StopParser.FileContext ctx) {
        globals = new GlobalScope(null);
        currentScope = globals;
    }

    @Override public void exitFile(StopParser.FileContext ctx) {

    }

    @Override public void enterModel(StopParser.ModelContext ctx) {
        ModelSymbol modelSymbol = new ModelSymbol(ctx, currentScope);
        currentScope.define(modelSymbol);
        saveScope(ctx, modelSymbol);
        currentScope = modelSymbol;
    }

    @Override public void exitModel(StopParser.ModelContext ctx) {
        ModelSymbol modelSymbol = (ModelSymbol) currentScope;
        if (modelSymbol.getAsync() && (modelSymbol.timeout == 0)){
            errors.add(new ValidationException("Asynchronous states " + modelSymbol.getName() + " must have a timeout defined"));
        }
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx){
        ModelSymbol modelSymbol = (ModelSymbol) currentScope;
        String numberString = ctx.NUMBER().getText();
        String modelTransitionName = ctx.transition().MODEL_TYPE().getText();
        modelSymbol.setTimeoutTransition(modelTransitionName);
        int timeout = Integer.parseInt(numberString);
        if(timeout == 0){
            errors.add(new ValidationException("Timeout must be greater than zero for " + modelSymbol.getName()));
        }else if (modelSymbol.timeout > 0){
            errors.add(new ValidationException("Timeout already defined for " + modelSymbol.getName()));
        }else {
            modelSymbol.timeout = timeout;
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
        if (ctx.type() != null && ctx.type().model_type() != null) {
            String modelName = ctx.type().model_type().getText();
            field = new ModelFieldSymbol(fieldName, modelName);
        }else if (ctx.type()!=null && ctx.type().scalar_type() != null){
            String typeName = ctx.type().scalar_type().getText();
            field = new ScalarFieldSymbol(fieldName, typeName);
        }else if (ctx.collection() != null && ctx.collection().type() != null){
            String typeName = ctx.collection().type().getText();
            boolean isState = ctx.collection().type().model_type() != null;
            field = new CollectionFieldSymbol(fieldName, typeName, isState);
        }
        if(field != null){
            if (ctx.async_source() != null){
                String asyncModel = ctx.async_source().MODEL_TYPE().getText();
                field.setAsyncSource(asyncModel);
            }
            currentScope.define(field);
        }
    }

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        TransitionSymbol transitionSymbol = new TransitionSymbol(modelName, currentScope);
        currentScope.define(transitionSymbol);
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        if (currentScope instanceof  ModelSymbol){
            ModelSymbol modelSymbol = (ModelSymbol)currentScope;
            modelSymbol.addErrorType(ctx.MODEL_TYPE().getText());
        }
    }

    void saveScope(ParserRuleContext ctx, Scope s){
        scopes.put(ctx, s);
    }
}
