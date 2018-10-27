package org.stop_lang.runtime.tests.helloworld;

import java.util.HashMap;

public class HelloRuntimeBase extends HashMap<String, Object> {
    private String name;

    public HelloRuntimeBase(String name){
        super();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
