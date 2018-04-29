package org.stop.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop.parser.StopBaseListener;
import org.stop.parser.StopParser;
import org.stop.symbols.ModelSymbol;

import java.util.ArrayList;
import java.util.List;

public class StopPhase extends StopBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();

    public StopPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
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
        Symbol symbol = globals.resolve(modelName);
        if(symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol modelSymbol = (ModelSymbol) symbol;
                boolean valid = findStop(modelSymbol);
                if (!valid){
                    errors.add(new ValidationException("Couldn't define transition \""+
                            modelName +"\" because a stopping state could not be reached"));
                }
            }
        }else{
            errors.add(new ValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    private boolean findStop(ModelSymbol modelSymbol){
        if (modelSymbol.getStop()){
            return true;
        }

        List<String> transitions = modelSymbol.getTransitions();
        transitions.addAll(modelSymbol.getErrorTypes());
        if(modelSymbol.getTimeoutTransition()!=null){
            transitions.add(modelSymbol.getTimeoutTransition());
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
