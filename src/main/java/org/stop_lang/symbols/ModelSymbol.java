package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.parser.StopParser;

import java.util.ArrayList;
import java.util.List;

public class ModelSymbol extends SymbolWithScope {
    private boolean async = false;
    private boolean stop =false;
    private boolean start =false;
    private boolean queue = false;
    private String returnTypeString = null;
    private List<String> errorTypes = new ArrayList<String>();
    private List<String> transitions = new ArrayList<String>();
    private List<String> enqueue = new ArrayList<String>();
    private int timeout = 0;
    private String timeoutTransition = null;
    private String returnType = null;
    private boolean returnCollection = false;

    public ModelSymbol(StopParser.ModelContext ctx, Scope enclosingScope){
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
        if (ctx.return_type()!=null){
            returnTypeString = ctx.return_type().getText();
            if (ctx.return_type().collection() != null) {
                if (ctx.return_type().collection().type() != null) {
                    returnType = ctx.return_type().collection().type().getText();
                    returnCollection = true;
                }
            }else if(ctx.return_type().type() != null){
                returnType = ctx.return_type().type().getText();
                returnCollection = false;
            }
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
    public boolean getQueue(){
        return queue;
    }

    public String getReturnTypeString(){
        return returnTypeString;
    }

    public String getReturnType(){
        return returnType;
    }

    public boolean isReturnCollection(){
        return returnCollection;
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

    public void setTimeout(int timeout){
        this.timeout = timeout;
    }

    public int getTimeout(){
        return this.timeout;
    }

    public void addEnqueue(String queue){
        enqueue.add(queue);
    }

    public List<String> getEnqueues(){
        return enqueue;
    }
}
