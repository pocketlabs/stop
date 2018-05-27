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

    public boolean transition(StateInstance from, StateInstance to){
        if (from == null || to == null){
            return false;
        }

        State errorState = from.getState().getErrors().get(to.getState().getName());
        State transitionState = from.getState().getTransitions().get(to.getState().getName());

        return (errorState != null) || (transitionState != null);
    }
}
