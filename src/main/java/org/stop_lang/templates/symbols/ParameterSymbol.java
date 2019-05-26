package org.stop_lang.templates.symbols;

import org.antlr.symtab.BaseSymbol;
import org.stop_lang.templates.parser.StopTemplatesParser;

public class ParameterSymbol  extends BaseSymbol {
    private StopTemplatesParser.Component_parameterContext ctx;

    public ParameterSymbol(StopTemplatesParser.Component_parameterContext ctx) {
        super(ctx.ID().getText());
        this.ctx = ctx;
    }

    public boolean isValue(){
        return ctx.component_parameter_value_or_collection().component_parameter_value()!=null;
    }

    public ParameterValueSymbol getValueSymbol(){
        return new ParameterValueSymbol(ctx.ID().getText(), ctx.component_parameter_value_or_collection().component_parameter_value());
    }

    public boolean isCollection(){
        return ctx.component_parameter_value_or_collection().component_parameter_value_collection()!=null;
    }

    public ParameterCollectionSymbol getCollectionSymbol(){
        return new ParameterCollectionSymbol(ctx.ID().getText(), ctx.component_parameter_value_or_collection().component_parameter_value_collection());
    }
}
