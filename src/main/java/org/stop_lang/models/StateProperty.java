package org.stop_lang.models;

import java.util.Map;

public class StateProperty extends Property {
    protected State state;

    public StateProperty(String name, State state, boolean collection, State provider, boolean optional, Map<String, String> providerMapping){
        super(name, PropertyType.STATE, collection, provider, optional, providerMapping);
        this.state = state;
    }

    public State getState(){
        return this.state;
    }

    @Override
    protected Class getClassForPropertyType(Property.PropertyType propertyType){
        return StateInstance.class;
    }
}
