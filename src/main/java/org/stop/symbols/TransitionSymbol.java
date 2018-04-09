package org.stop.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;

public class TransitionSymbol  extends SymbolWithScope {
    public TransitionSymbol(String name, Scope enclosingScope){
        super(name);
        setScope(enclosingScope);
    }
}
