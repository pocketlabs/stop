package org.stop_lang.tests.runtime.enums;

import java.util.HashMap;

public class EnumRuntimeBase extends HashMap<String, Object> {
    private String name;

    public EnumRuntimeBase(String name){
        super();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
