package org.stop_lang.templates.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.templates.parser.StopTemplatesParser;

public class CollectionSymbol  extends SymbolWithScope {
    private boolean not = false;

    public CollectionSymbol(StopTemplatesParser.CollectionContext ctx, Scope enclosingScope) {
        super ( ctx.ID() != null ? ctx.ID().getText() : ctx.REFERENCE().getText() );
        setScope(enclosingScope);

        not = (ctx.NOT() != null);
    }

    public boolean isNegated(){
        return not;
    }
}
