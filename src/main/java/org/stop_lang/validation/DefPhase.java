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
        if (modelSymbol.isAsync() && (modelSymbol.getTimeout() == null)){
            errors.add(new StopValidationException("Asynchronous states " + modelSymbol.getName() + " must have a timeout defined"));
        }
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void exitTimeout(StopParser.TimeoutContext ctx){
        ModelSymbol modelSymbol = (ModelSymbol) currentScope;
        String numberString = ctx.NUMBER().getText();
        int timeout = Integer.parseInt(numberString);
        if(timeout == 0){
            errors.add(new StopValidationException("Timeout must be greater than zero for " + modelSymbol.getName()));
        }else {
            TimeoutSymbol timeoutSymbol = new TimeoutSymbol(ctx, currentScope, packageName);
            modelSymbol.setTimeout(timeoutSymbol);
        }
    }

    @Override public void enterEnumeration(StopParser.EnumerationContext ctx) {
        EnumSymbol enumSymbol = new EnumSymbol(ctx, currentScope, packageName);
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
        StopFieldSymbol field = null;

        if (ctx.type() != null && ctx.type().model_type() != null) {
            field = new ModelFieldSymbol(ctx, packageName);
        }else if (ctx.type()!=null && ctx.type().scalar_type() != null){
            field = new ScalarFieldSymbol(ctx);
        }else if (ctx.collection() != null && ctx.collection().type() != null){
            field = new CollectionFieldSymbol(ctx, packageName);
        }

        if(field != null){
            if (ctx.async_source() != null){
                DynamicModelSymbol dynamicModelSymbol = new DynamicModelSymbol(ctx, currentScope, packageName);
                field.setDynamicModel(dynamicModelSymbol);
            }
            if ( ctx.OPTIONAL() != null){
                field.setOptional(true);
            }
            currentScope.define(field);
        }
    }

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        TransitionSymbol transitionSymbol = new TransitionSymbol(ctx, currentScope, packageName);
        if (currentScope instanceof  ModelSymbol){
            ((ModelSymbol)currentScope).addTransition(transitionSymbol);
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        EnqueueSymbol enqueueSymbol = new EnqueueSymbol(ctx, currentScope, packageName);
        if (currentScope instanceof  ModelSymbol){
            ((ModelSymbol)currentScope).addEnqueue(enqueueSymbol);
        }
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        if (currentScope instanceof  ModelSymbol){
            ModelSymbol modelSymbol = (ModelSymbol)currentScope;
            ThrowSymbol throwSymbol = new ThrowSymbol(ctx, currentScope, packageName);
            modelSymbol.addError(throwSymbol);
        }
    }

    void saveScope(ParserRuleContext ctx, Scope s){
        scopes.put(ctx, s);
    }
}
