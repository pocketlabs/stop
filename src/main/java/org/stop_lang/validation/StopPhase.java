package org.stop_lang.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.EnqueueSymbol;
import org.stop_lang.symbols.ModelSymbol;
import org.stop_lang.symbols.ThrowSymbol;
import org.stop_lang.symbols.TransitionSymbol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StopPhase extends StopBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();
    private String packageName = null;

    public StopPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
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

    @Override public void exitTransition(StopParser.TransitionContext ctx) {
        TransitionSymbol transitionSymbol = ((ModelSymbol)currentScope).getTransition(ctx);
        if(transitionSymbol == null){
            errors.add(new StopValidationException("Couldn't validate transition because it was not defined"));
        }else {
            String modelName = transitionSymbol.getName();
            Symbol symbol = globals.resolve(modelName);
            if (symbol != null) {
                if (symbol instanceof ModelSymbol) {
                    ModelSymbol modelSymbol = (ModelSymbol) symbol;
                    boolean valid = findStop(modelSymbol);
                    if (!valid) {
                        errors.add(new StopValidationException("Couldn't define transition \"" +
                                modelName + "\" because a stopping state could not be reached"));
                    }
                }
            } else {
                errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
            }
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        EnqueueSymbol enqueueSymbol = ((ModelSymbol)currentScope).getEnqueue(ctx);
        if(enqueueSymbol == null){
            errors.add(new StopValidationException("Couldn't define enqueue because it was not defined"));
        }else {
            String modelName = enqueueSymbol.getName();
            Symbol symbol = globals.resolve(modelName);
            if (symbol != null) {
                if (symbol instanceof ModelSymbol) {
                    ModelSymbol modelSymbol = (ModelSymbol) symbol;
                    boolean valid = findStop(modelSymbol);
                    if (!valid) {
                        errors.add(new StopValidationException("Couldn't define enqueue \"" +
                                modelName + "\" because a stopping state could not be reached"));
                    }
                }
            } else {
                errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't defined"));
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
                boolean valid = findStop(throwModelSymbol);
                if (!valid) {
                    errors.add(new StopValidationException("Couldn't define error \"" +
                            modelName + "\" because a stopping state could not be reached"));
                }
            }
        }
    }

    private boolean findStop(ModelSymbol modelSymbol){
        if (modelSymbol.isStop()){
            return true;
        }

        Set<String> transitions = new HashSet<>();
        for (TransitionSymbol transitionSymbol : modelSymbol.getTransitions()){
            transitions.add(transitionSymbol.getName());
        }
        for (ThrowSymbol throwSymbol : modelSymbol.getErrors()) {
            transitions.add(throwSymbol.getName());
        }
        if(modelSymbol.getTimeout()!=null){
            transitions.add(modelSymbol.getTimeout().getName());
        }

        if (modelSymbol.getTransitions().isEmpty()){
            return false;
        }

        boolean foundStop = true;

        for (String transition : transitions){
            Symbol symbol = globals.resolve(transition);
            if(symbol != null) {
                if (symbol instanceof ModelSymbol){
                    ModelSymbol transitionModelSymbol = (ModelSymbol) symbol;
                    if (!findStop(transitionModelSymbol)){
                        foundStop = false;
                    }
                }else{
                    foundStop = false;
                }
            }else {
                foundStop = false;
            }
        }

        return foundStop;
    }
}
