package org.stop_lang.models;

import java.util.Collection;

public class Enumeration {

    protected Collection<String> values;
    protected  String name;

    public Enumeration(String name, Collection<String> values){
        this.name = name;
        this.values = values;
    }

    public String getName(){
        return this.name;
    }

    public Collection<String> getValues(){
        return this.values;
    }
}
