package org.stop_lang.templates.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.templates.parser.StopTemplatesParser;

public class StatementSymbol  extends SymbolWithScope {
    public StatementSymbol(StopTemplatesParser.StatementContext ctx, Scope enclosingScope) {
        super(ctx.getText());
        setScope(enclosingScope);
    }
}
