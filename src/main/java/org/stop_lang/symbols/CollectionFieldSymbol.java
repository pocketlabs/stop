package org.stop_lang.symbols;

public class CollectionFieldSymbol extends StopFieldSymbol {
    protected boolean state = false;
    public CollectionFieldSymbol(String name, String typeName, boolean state, String packageName){
        super(name, typeName, packageName);
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    @Override
    public String getFullTypeName(){
        if (state) {
            return getFullModelName(typeName);
        }
        return typeName;
    }
}
