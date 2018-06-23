package org.stop_lang.symbols;

import org.antlr.symtab.FieldSymbol;

public class StopFieldSymbol extends FieldSymbol {
    private String typeName;
    private String asyncSource;
    private boolean optional = false;

    public StopFieldSymbol(String name, String typeName){
        super(name);
        this.typeName = typeName;
    }

    public String getTypeName(){
        return typeName;
    }

    public void setAsyncSource(String asyncSource){
        this.asyncSource = asyncSource;
    }

    public String getAsyncSource() {
        return this.asyncSource;
    }

    public void setOptional(boolean optional){
        this.optional = optional;
    }

    public boolean isOptional(){
        return this.optional;
    }
}
