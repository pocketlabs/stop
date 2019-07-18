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
    private String packageName = null;

    public TransitionPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes) {
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
        String modelName = getFullModelName(ctx.model_type().getText());
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

        Symbol symbol = globalsResolveWithPackage(modelName);
        if(modelSymbol != null && symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol transitionSymbol = (ModelSymbol)symbol;
                if (transitionSymbol.getQueue()){
                    errors.add(new StopValidationException("Couldn't define transition because " + modelName + " is a queue state"));
                }else {
                    modelSymbol.addTransition(modelName);
                }
            }
        }else{
            errors.add(new StopValidationException("Couldn't define transition because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitEnqueue(StopParser.EnqueueContext ctx) {
        String modelName = ctx.model_type().getText();
        ModelSymbol modelSymbol = null;
        if (currentScope instanceof ModelSymbol){
            modelSymbol = (ModelSymbol) currentScope;
        }else if (currentScope.getEnclosingScope() instanceof ModelSymbol){
            modelSymbol = (ModelSymbol)currentScope.getEnclosingScope();
        }

        Symbol symbol = globalsResolveWithPackage(modelName);
        if(modelSymbol != null && symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol transitionSymbol = (ModelSymbol)symbol;
                if (transitionSymbol.getQueue()){
                    modelSymbol.addEnqueue(modelName);
                }else {
                    errors.add(new StopValidationException("Couldn't define enqueue because " + modelName + " isn't a queue state"));
                }
            }
        }else{
            errors.add(new StopValidationException("Couldn't define queue because " + modelName + " isn't defined"));
        }
    }

    @Override public void exitThrow_parameter(StopParser.Throw_parameterContext ctx) {
        String modelName = ctx.model_type().getText();
        ModelSymbol modelSymbol = (ModelSymbol)currentScope;

        Symbol symbol = globalsResolveWithPackage(modelName);
        if(symbol != null) {
            if (symbol instanceof ModelSymbol){
                ModelSymbol transitionSymbol = (ModelSymbol)symbol;
                if (transitionSymbol.getQueue()){
                    errors.add(new StopValidationException("Couldn't define transition because " + modelName + " is a queue state"));
                }else {
                    modelSymbol.addErrorType(modelName);
                }
            }
        }else{
            errors.add(new StopValidationException("Couldn't define thrown transition because " + modelName + " isn't defined"));
        }
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
