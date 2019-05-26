package org.stop_lang.templates.symbols;

import org.antlr.symtab.BaseSymbol;
import org.stop_lang.templates.parser.StopTemplatesParser;

import java.util.ArrayList;
import java.util.Collection;

public class ParameterCollectionSymbol  extends BaseSymbol {
    private StopTemplatesParser.Component_parameter_value_collectionContext ctx;
    private Collection<ParameterValueSymbol> values = new ArrayList<ParameterValueSymbol>();

    public ParameterCollectionSymbol(String name, StopTemplatesParser.Component_parameter_value_collectionContext ctx) {
        super(name);
        this.ctx = ctx;

        for(StopTemplatesParser.Component_parameter_valueContext parameterCtx : ctx.component_parameter_value()){
            values.add(new ParameterValueSymbol(name, parameterCtx));
        }
    }

    public Collection<ParameterValueSymbol> getValueSymbols(){
        return values;
    }
}
