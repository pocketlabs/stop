package org.stop_lang.templates.symbols;

import org.antlr.symtab.BaseSymbol;
import org.stop_lang.models.Property;

public class ReferenceSymbol  extends BaseSymbol {
    private Property property;

    public ReferenceSymbol(String name, Property property) {
        super(name);
        this.property = property;
    }

    public Property getProperty(){
        return this.property;
    }
}
