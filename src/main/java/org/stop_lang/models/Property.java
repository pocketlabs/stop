package org.stop_lang.models;

import java.nio.ByteBuffer;

public class Property {
    public enum PropertyType {
        DOUBLE,
        FLOAT,
        INT32,
        INT64,
        UINT32,
        UINT64,
        SINT32,
        SINT64,
        FIXED32,
        FIXED64,
        SFIXED32,
        SFIXED64,
        BOOL,
        STRING,
        BYTES,
        TIMESTAMP,
        STATE,
        ENUM
    }

    protected String name;
    protected PropertyType type;
    protected State provider;
    protected boolean collection;
    protected boolean optional;

    public Property(String name, PropertyType type, boolean collection, State provider, boolean optional){
        this.name = name;
        this.type = type;
        this.provider = provider;
        this.collection = collection;
        this.optional = optional;
    }

    public String getName(){
        return this.name;
    }

    public PropertyType getType(){
        return this.type;
    }

    public Class getClassType(){
        return getClassForPropertyType(this.type);
    }

    protected Class getClassForPropertyType(Property.PropertyType propertyType){
        switch(propertyType){
            case DOUBLE:
                return Double.class;
            case FLOAT:
                return Float.class;
            case INT32:
                return Integer.class;
            case INT64:
                return Long.class;
            case UINT32:
                return Integer.class;
            case UINT64:
                return Long.class;
            case SINT32:
                return Integer.class;
            case SINT64:
                return Long.class;
            case FIXED32:
                return Integer.class;
            case FIXED64:
                return Long.class;
            case SFIXED32:
                return Integer.class;
            case SFIXED64:
                return Long.class;
            case BOOL:
                return Boolean.class;
            case STRING:
                return String.class;
            case BYTES:
                return ByteBuffer.class;
            default:
                throw new IllegalArgumentException("Invalid type supplied");
        }
    }

    public State getProvider(){
        return provider;
    }

    public boolean isCollection(){
        return collection;
    }

    public boolean isOptional() { return optional; }
}
