package org.stop_lang.templates.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.templates.parser.StopTemplatesParser;

import java.util.HashMap;
import java.util.Map;

public class ComponentSymbol extends SymbolWithScope {
    private Map<String, Symbol> parameters = new HashMap<>();

    public ComponentSymbol(StopTemplatesParser.ComponentContext ctx, Scope enclosingScope) {
        super(ctx.COMPONENT_TYPE().getText());
        setScope(enclosingScope);
    }

    public Map<String, Symbol> getParameters(){
        return parameters;
    }

    public void addParameter(String name, Symbol value){
        parameters.put(name, value);
    }
}
