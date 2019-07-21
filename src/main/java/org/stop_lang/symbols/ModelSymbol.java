package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stop_lang.parser.StopParser;

import java.util.*;

public class ModelSymbol extends SymbolWithScope {
    private boolean async = false;
    private boolean stop =false;
    private boolean start =false;
    private boolean queue = false;
    private Map<StopParser.Throw_parameterContext, ThrowSymbol> errors = new HashMap<>();
    private Map<StopParser.TransitionContext, TransitionSymbol> transitions = new HashMap<>();
    private Map<StopParser.EnqueueContext, EnqueueSymbol> enqueues = new HashMap<>();
    private TimeoutSymbol timeout;
    private ReturnSymbol returnSymbol = null;
    private String fullName;
    private String packageName;

    public ModelSymbol(StopParser.ModelContext ctx, Scope enclosingScope, String defaultPackageName){
        super(ctx.MODEL_TYPE().getText());
        setScope(enclosingScope);
        if(ctx.STOP() != null) {
            stop = true;
        } else if (ctx.START() != null){
            start = true;
        }else if(ctx.ASYNC() != null) {
            async = true;
        } else if (ctx.QUEUE() != null){
            queue = true;
        }

        String name = ctx.MODEL_TYPE().getText();
        packageName = defaultPackageName;

        ParseTree p = ctx.getParent().getChild(0);
        if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
            StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
            packageName = decl.packageName().getText();
        }

        if (packageName!=null){
            name = packageName + "." + name;
        }

        if (ctx.return_type()!=null){
            returnSymbol = new ReturnSymbol(ctx, enclosingScope, packageName);
        }

        fullName = name;
    }

    @Override
    public String getName(){
        return fullName;
    }

    public boolean isAsync(){
        return async;
    }
    public boolean isStop(){
        return stop;
    }
    public boolean isStart(){
        return start;
    }
    public boolean isQueue(){
        return queue;
    }

    public ReturnSymbol getReturn(){
        return returnSymbol;
    }

    public Collection<ThrowSymbol> getErrors(){
        return errors.values();
    }

    public void addError(ThrowSymbol throwSymbol){
        errors.put(throwSymbol.getContext(), throwSymbol);
    }

    public ThrowSymbol getError(StopParser.Throw_parameterContext ctx){
        return errors.get(ctx);
    }

    public void addTransition(TransitionSymbol transition)
    {
        transitions.put(transition.getContext(), transition);
    }

    public TransitionSymbol getTransition(StopParser.TransitionContext ctx){
        return transitions.get(ctx);
    }

    public Collection<TransitionSymbol> getTransitions(){
        return transitions.values();
    }

    public void setTimeout(TimeoutSymbol timeout){
        this.timeout = timeout;
    }

    public TimeoutSymbol getTimeout(){
        return this.timeout;
    }

    public void addEnqueue(EnqueueSymbol enqueueSymbol){
        enqueues.put(enqueueSymbol.getContext(), enqueueSymbol);
    }

    public EnqueueSymbol getEnqueue(StopParser.EnqueueContext ctx){
        return enqueues.get(ctx);
    }

    public Collection<EnqueueSymbol> getEnqueues(){
        return enqueues.values();
    }
}
