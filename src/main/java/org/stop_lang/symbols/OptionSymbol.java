package org.stop_lang.symbols;

import org.antlr.symtab.FieldSymbol;

public class OptionSymbol extends FieldSymbol {
    private String value;

    public OptionSymbol(String name, String value){
        super(name);
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
