package org.stop.models;

public class StateProperty extends Property {
    protected State state;

    public StateProperty(String name, State state, boolean collection, State provider){
        super(name, PropertyType.STATE, collection, provider);
        this.state = state;
    }

    public State getState(){
        return this.state;
    }

    @Override
    protected Class getClassForPropertyType(Property.PropertyType propertyType){
        return State.class;
    }
}
