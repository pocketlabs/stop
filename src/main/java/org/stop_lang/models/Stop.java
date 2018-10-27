package org.stop_lang.models;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.stop_lang.validation.Validator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Stop {
    private Map<String, State> states;

    public Stop(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        this.states = Validator.getStates(input);
    }

    public Stop(CharStream input) throws IOException {
        this.states = Validator.getStates(input);
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
