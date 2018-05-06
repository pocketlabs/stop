package org.stop_lang.models;

public class AsyncState extends State {
    protected int timeout;
    protected State timeoutTransition;

    public AsyncState(String name, int timeout){
        super(name);
        this.type = StateType.ASYNC;
        this.timeout = timeout;
    }

    public int getTimeout(){
        return this.timeout;
    }

    public void setTimeoutTransition(State timeoutTransition){
        this.timeoutTransition = timeoutTransition;
    }

    public State getTimeoutTransition(){
        return this.timeoutTransition;
    }
}
