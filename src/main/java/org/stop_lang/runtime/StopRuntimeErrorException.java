package org.stop_lang.runtime;

import org.stop_lang.models.StateInstance;

public class StopRuntimeErrorException extends Exception{
    private StateInstance errorStateInstance;
    private StateInstance contextStateInstance;

    public StopRuntimeErrorException(StateInstance errorStateInstance){
        super("StopRuntimeErrorException");

        this.errorStateInstance = errorStateInstance;
    }

    public StopRuntimeErrorException(StateInstance errorStateInstance, StateInstance contextStateInstance){
        super("StopRuntimeErrorException");

        this.errorStateInstance = errorStateInstance;
        this.contextStateInstance = contextStateInstance;
    }

    public StateInstance getErrorStateInstance(){
        return this.errorStateInstance;
    }

    public StateInstance getContextStateInstance(){
        return this.contextStateInstance;
    }
}
