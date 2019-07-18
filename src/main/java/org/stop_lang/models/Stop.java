package org.stop_lang.models;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.stop_lang.validation.Validator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Stop {
    private Map<String, State> states;
    private Map<String, Enumeration> enumerations;

    public static Stop fromFilename(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return fromInput(input);
    }

    public static Stop fromInput(CharStream input) {
        return Validator.getStop(input);
    }

    public Stop(Map<String, State> states, Map<String, Enumeration> enumerations){
        this.states = states;
        this.enumerations = enumerations;
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
    public Map<String, Enumeration> getEnumerations(){
        return this.enumerations;
    }
}
