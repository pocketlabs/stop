package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;

public class EnqueueSymbol extends SymbolWithScope {
    public EnqueueSymbol(String name, Scope enclosingScope){
        super(name);
        setScope(enclosingScope);
    }
}
