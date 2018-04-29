package org.stop.models;

public class EnumerationProperty extends Property {
    protected Enumeration enumeration;

    public EnumerationProperty(String name, Enumeration enumeration, boolean collection, State provider){
        super(name, PropertyType.ENUM, collection, provider);
        this.enumeration = enumeration;
    }

    public Enumeration getEnumeration(){
        return this.enumeration;
    }

    @Override
    protected Class getClassForPropertyType(Property.PropertyType propertyType){
        return Enumeration.class;
    }
}

