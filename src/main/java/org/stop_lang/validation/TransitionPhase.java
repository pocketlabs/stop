package org.stop_lang.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.ModelSymbol;

import java.util.ArrayList;
import java.util.List;

public class TransitionPhase extends StopBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();

    public TransitionPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
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

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        ModelSymbol modelSymbol = null;
        if (currentScope instanceof ModelSymbol){
            if (ctx.getParent() instanceof StopParser.TimeoutContext){
                return;
            }else {
                modelSymbol = (ModelSymbol) currentScope;
            }
        }else if (currentScope.getEnclosingScope() instanceof ModelSymbol){
            modelSymbol = (ModelSymbol)currentScope.getEnclosingScope();
        }

        Symbol symbol = globals.resolve(modelName);
        if(modelSymbol != null && symbol != null) {
            if (symbol instanceof ModelSymbol){
                modelSymbol.addTransition(modelName);
            }
        }else{
            errors.add(new ValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        String modelName = ctx.MODEL_TYPE().getText();
        ModelSymbol modelSymbol = (ModelSymbol)currentScope;

        Symbol symbol = globals.resolve(modelName);
        if(symbol != null) {
            if (symbol instanceof ModelSymbol){
                modelSymbol.addErrorType(modelName);
            }
        }else{
            errors.add(new ValidationException("Couldn't define thrown transition because " + modelName + " isn't defined"));
        }
    }
}