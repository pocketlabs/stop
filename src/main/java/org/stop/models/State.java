package org.stop.models;

import java.util.Map;
import java.util.TreeMap;

public class State {
    public enum StateType {
        SYNC,
        ASYNC,
        START,
        STOP
    }

    protected String name;
    protected Map<String, State> transitions;
    protected Map<String, State> errors;
    protected Map<String, Property> properties;
    protected Map<String, Enumeration> enumerations;
    protected StateType type;

    public State(String name){
        this.name = name;
        this.transitions = new TreeMap<String, State>();
        this.errors = new TreeMap<String, State>();
        this.properties = new TreeMap<String, Property>();
        this.enumerations = new TreeMap<String, Enumeration>();
        this.type = StateType.SYNC;
    }

    public State(String name, StateType type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return this.name;
    }

    public void setTransitions(TreeMap<String, State> transitions){
        this.transitions = transitions;
    }

    public Map<String, State> getTransitions(){
        return this.transitions;
    }

    public void setErrors(TreeMap<String, State> errors){
        this.errors = errors;
    }

    public Map<String, State> getErrors(){
        return this.errors;
    }

    public void setProperties(TreeMap<String, Property> properties){
        this.properties = properties;
    }

    public Map<String, Property> getProperties(){
        return this.properties;
    }

    public void setEnumerations(TreeMap<String, Enumeration> enumerations){
        this.enumerations = enumerations;
    }

    public Map<String, Enumeration> getEnumerations(){
        return this.enumerations;
    }

    public StateType getType(){
        return this.type;
    }

    public boolean isSync(){
        return this.type == StateType.SYNC;
    }

    public boolean isAsync(){
        return this.type == StateType.ASYNC;
    }

    public boolean isStart(){
        return this.type == StateType.START;
    }

    public boolean isStop(){
        return this.type == StateType.STOP;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof State){
            State otherState = (State)o;
            return otherState.getName().equalsIgnoreCase(getName());
        }
        return false;
    }
}
