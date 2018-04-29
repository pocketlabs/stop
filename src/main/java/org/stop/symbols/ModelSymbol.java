package org.stop.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop.parser.StopParser;

import java.util.ArrayList;
import java.util.List;

public class ModelSymbol extends SymbolWithScope {
    private boolean async = false;
    private boolean stop =false;
    private boolean start =false;
    private String asyncReturnType = null;
    private List<String> errorTypes = new ArrayList<String>();
    private List<String> transitions = new ArrayList<String>();
    public int timeout = 0;
    private String timeoutTransition = null;

    public ModelSymbol(StopParser.ModelContext ctx, Scope enclosingScope){
        super(ctx.MODEL_TYPE().getText());
        setScope(enclosingScope);
        if(ctx.STOP() != null) {
            stop = true;
        } else if (ctx.START() != null){
            start = true;
        }else if(ctx.ASYNC() != null) {
            async = true;
        }
        if (ctx.return_type()!=null){
            asyncReturnType = ctx.return_type().getText();
        }
    }

    public boolean getAsync(){
        return async;
    }
    public boolean getStop(){
        return stop;
    }
    public boolean getStart(){
        return start;
    }

    public String getAsyncReturnType(){
        return asyncReturnType;
    }

    public List<String> getErrorTypes(){
        return errorTypes;
    }

    public void addErrorType(String t){
        errorTypes.add(t);
    }

    public void addTransition(String transition){
        transitions.add(transition);
    }

    public List<String> getTransitions(){
        return transitions;
    }

    public void setTimeoutTransition(String transition){
        this.timeoutTransition = transition;
    }

    public String getTimeoutTransition(){
        return this.timeoutTransition;
    }
}
