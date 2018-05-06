package org.stop_lang.symbols;

public class CollectionFieldSymbol extends StopFieldSymbol {
    protected boolean state = false;
    public CollectionFieldSymbol(String name, String typeName, boolean state){
        super(name, typeName);
        this.state = state;
    }

    public boolean isState() {
        return state;
    }
}
