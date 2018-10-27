package org.stop_lang.runtime;

import org.stop_lang.models.State;
import org.stop_lang.models.StateInstance;
import org.stop_lang.models.Stop;

public class StopRuntime {
    private Stop stop;

    public StopRuntime(Stop stop){
        this.stop = stop;
    }

    public Stop getStop(){
        return this.stop;
    }

    public void transition(StateInstance from, StateInstance to) throws StopRuntimeException {
        if (from == null || to == null){
            throw new StopRuntimeException("From and to state instances must be defined");
        }

        State errorState = from.getState().getErrors().get(to.getState().getName());
        State transitionState = from.getState().getTransitions().get(to.getState().getName());

        if ((errorState == null) && (transitionState == null)){
            throw new StopRuntimeException("Could not find state to transition to called " + to.getState().getName());
        }
    }

    public void queue(StateInstance from, StateInstance queue) throws StopRuntimeException {
        if (from == null || queue == null){
            throw new StopRuntimeException("From and queue state instances must be defined");
        }

        State queueState = from.getState().getEnqueues().get(queue.getState().getName());

        if (queueState == null){
            throw new StopRuntimeException("Could not find queue " + queue.getState().getName());
        }

        if(!queueState.isQueue()){
            throw new StopRuntimeException("Invalid queue state");
        }
    }
}
