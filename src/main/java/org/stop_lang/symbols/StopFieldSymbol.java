package org.stop_lang.symbols;

import org.antlr.symtab.FieldSymbol;

import java.util.Map;

public class StopFieldSymbol extends FieldSymbol {
    protected String typeName;
    protected String asyncSource;
    protected boolean optional = false;
    protected Map<String, String> asyncMapping;
    protected String packageName;

    public StopFieldSymbol(String name, String typeName, String packageName){
        super(name);
        this.typeName = typeName;
        this.packageName = packageName;
    }

    public String getTypeName(){
        return typeName;
    }

    public String getFullTypeName(){
        return typeName;
    }

    public void setAsyncSource(String asyncSource){
        this.asyncSource = asyncSource;
    }

    public String getAsyncSource() {
        return this.asyncSource;
    }

    public void setAsyncMapping(Map<String, String> asyncMapping){
        this.asyncMapping = asyncMapping;
    }

    public Map<String, String> getAsyncMapping() {
        return this.asyncMapping;
    }

    public void setOptional(boolean optional){
        this.optional = optional;
    }

    public boolean isOptional(){
        return this.optional;
    }

    protected String getFullModelName(String name){
        if (!isReference(name) && (packageName!=null)){
            return packageName + "." + name;
        }
        return name;
    }

    protected boolean isReference(String name){
        return name.contains(".");
    }
}
