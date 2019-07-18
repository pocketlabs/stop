package org.stop_lang.symbols;

public class ModelFieldSymbol extends StopFieldSymbol {
    public ModelFieldSymbol(String name, String typeName, String packageName){
        super(name, typeName, packageName);
    }

    @Override
    public String getFullTypeName(){
        return getFullModelName(typeName);
    }
}
