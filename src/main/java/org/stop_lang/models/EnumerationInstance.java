package org.stop_lang.models;

public class EnumerationInstance {
    private Enumeration enumeration;
    private String value;

    public EnumerationInstance(Enumeration enumeration, String value) throws Exception {
        this.enumeration = enumeration;
        this.value = value;

        if(!enumeration.getValues().contains(value)){
            throw new Exception("Invalid value for enumeration: "+ value);
        }
    }

    public Enumeration getEnumeration(){
        return this.enumeration;
    }

    public String getValue(){
        return this.value;
    }
}
