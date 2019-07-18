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
        String modelName = ctx.model_type().getText();
        Symbol symbol = globalsResolveWithPackage(modelName);
        if(symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol modelSymbol = (ModelSymbol) symbol;
                boolean valid = findStop(modelSymbol);
                if (!valid){
                    errors.add(new StopValidationException("Couldn't define transition \""+
                            modelName +"\" because a stopping state could not be reached"));
                }
            }
        }else{
            errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        String modelName = ctx.model_type().getText();
        Symbol symbol = globalsResolveWithPackage(modelName);
        if(symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol modelSymbol = (ModelSymbol) symbol;
                boolean valid = findStop(modelSymbol);
                if (!valid){
                    errors.add(new StopValidationException("Couldn't define enqueue \""+
                            modelName +"\" because a stopping state could not be reached"));
                }
            }
        }else{
            errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't defined"));
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
            Symbol symbol = globalsResolveWithPackage(transition);
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
