package org.stop_lang.templates.symbols;

import org.antlr.symtab.BaseSymbol;
import org.stop_lang.templates.parser.StopTemplatesParser;

public class ParameterValueSymbol extends BaseSymbol {
    private StopTemplatesParser.Component_parameter_valueContext ctx;

    public ParameterValueSymbol(String name, StopTemplatesParser.Component_parameter_valueContext ctx) {
        super(name);
        this.ctx = ctx;
    }

    public boolean isValueID(){
        return ctx.ID() != null;
    }

    public String getValueID(){
        return ctx.ID().getText();
    }

    public boolean isValueReference(){
        return ctx.REFERENCE() != null;
    }

    public String getValueReference(){
        return ctx.REFERENCE().getText();
    }

    public boolean isValueString(){
        return ctx.STRING_LITERAL() != null;
    }

    public boolean isValueNumber(){
        return ctx.NUMBER_LITERAL() != null;
    }

    public boolean isValueBoolean(){
        return ctx.BOOL_LITERAL() != null;
    }

    public Object getValue(){
        if (ctx.STRING_LITERAL() != null) {
            return getStringLiteral();
        }
        if (ctx.NUMBER_LITERAL() != null) {
            return getNumberLiteral();
        }
        if (ctx.BOOL_LITERAL() != null) {
            return getBooleanLiteral();
        }
        return ctx.getText();
    }

    public String getStringLiteral(){
        String literalValue = ctx.STRING_LITERAL().getText();
        if ((literalValue!=null) && literalValue.length() > 2){
            return literalValue.substring(1, literalValue.length()-1);
        }
        return literalValue;
    }

    public Boolean getBooleanLiteral(){
        return ctx.BOOL_LITERAL().getText().equalsIgnoreCase("true");
    }

    public Double getNumberLiteral(){
        return Double.parseDouble(ctx.NUMBER_LITERAL().getText());
    }
}
