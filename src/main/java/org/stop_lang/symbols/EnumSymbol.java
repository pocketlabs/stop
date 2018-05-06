package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.parser.StopParser;

import java.util.ArrayList;
import java.util.List;

public class EnumSymbol extends SymbolWithScope {
    private List<String> values = new ArrayList<String>();

    public EnumSymbol(StopParser.EnumerationContext ctx, Scope enclosingScope){
        super(ctx.enum_type().MODEL_TYPE().getText());
        setScope(enclosingScope);
    }

    public List<String> getValues(){
        return values;
    }

    public void addValue(String t){
        values.add(t);
    }
}
