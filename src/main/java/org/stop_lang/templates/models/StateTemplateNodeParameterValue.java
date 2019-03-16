package org.stop_lang.templates.models;

public class StateTemplateNodeParameterValue {
    public enum Type {
        LITERAL,
        ID,
        COLLECTION,
        REFERENCE
    }
    protected Type type;
    protected Object value;

    public StateTemplateNodeParameterValue(Type type, Object value){
        this.type = type;
        this.value = value;
    }

    public Type getType(){
        return type;
    }

    public Object getValue(){
        return value;
    }
}
