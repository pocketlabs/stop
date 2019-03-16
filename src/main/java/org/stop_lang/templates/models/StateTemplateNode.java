package org.stop_lang.templates.models;

import org.stop_lang.models.State;

import java.util.HashMap;
import java.util.Map;

public class StateTemplateNode extends TemplateNode {
    private State state;
    private Map<String, StateTemplateNodeParameterValue> params = new HashMap<>();

    public StateTemplateNode(State state){
        super();
        this.state = state;
    }

    public StateTemplateNode(TemplateNode parent, State state){
        super(parent);
        this.state = state;
    }

    public State getState(){
        return state;
    }

    public Map<String, StateTemplateNodeParameterValue> getParameters(){
        return params;
    }

    public void addParameter(String key, StateTemplateNodeParameterValue value){
        params.put(key, value);
    }
}
