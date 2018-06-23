package org.stop_lang.models;

public class StateProperty extends Property {
    protected State state;

    public StateProperty(String name, State state, boolean collection, State provider, boolean optional){
        super(name, PropertyType.STATE, collection, provider, optional);
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
