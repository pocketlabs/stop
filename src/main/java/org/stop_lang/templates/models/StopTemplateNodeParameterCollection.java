package org.stop_lang.templates.models;

import java.util.ArrayList;
import java.util.Collection;

public class StopTemplateNodeParameterCollection extends StateTemplateNodeParameterValue {
    private Collection<StateTemplateNodeParameterValue> values = new ArrayList<StateTemplateNodeParameterValue>();

    public StopTemplateNodeParameterCollection(Type type){
        super(type, null);
    }

    public void addParameterValue(StateTemplateNodeParameterValue value){
        values.add(value);
    }

    public Collection<StateTemplateNodeParameterValue> getValues(){
        return values;
    }
}
