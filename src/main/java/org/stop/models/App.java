package org.stop.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class App {
    private Map<String, State> states;

    public App(TreeMap<String, State> states){
        this.states = states;
    }

    public Set<State> getStartStates(){
        Set<State> startStates = new HashSet<State>();
        for (State state : states.values()){
            if (state.isStart()){
                startStates.add(state);
            }
        }
        return startStates;
    }

    public Map<String, State> getStates(){
        return this.states;
    }
}
