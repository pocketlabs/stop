package org.stop_lang.templates.validation;

import org.antlr.symtab.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.templates.parser.StopTemplatesBaseListener;
import org.stop_lang.templates.parser.StopTemplatesParser;
import org.stop_lang.templates.symbols.CollectionSymbol;
import org.stop_lang.templates.symbols.ComponentSymbol;
import org.stop_lang.templates.symbols.ConditionalSymbol;
import org.stop_lang.templates.symbols.ParameterSymbol;
import org.stop_lang.templates.symbols.StatementSymbol;

import java.util.ArrayList;
import java.util.List;

public class DefPhase extends StopTemplatesBaseListener {
    public ParseTreeProperty<Scope> scopes = new ParseTreeProperty<Scope>();
    public GlobalScope globals;
    public Scope currentScope;
    public List<Exception> errors = new ArrayList<Exception>();

    @Override public void enterFile(StopTemplatesParser.FileContext ctx) {
        globals = new GlobalScope(null);
        currentScope = globals;
    }

    @Override public void exitFile(StopTemplatesParser.FileContext ctx) { }

    @Override public void enterStatement(StopTemplatesParser.StatementContext ctx) {
        StatementSymbol statementSymbol = new StatementSymbol(ctx, currentScope);
        saveScope(ctx, statementSymbol);
        currentScope = statementSymbol;
    }

    @Override public void exitStatement(StopTemplatesParser.StatementContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterNested_statements(StopTemplatesParser.Nested_statementsContext ctx) {
        LocalScope localScope = new LocalScope(currentScope);
        saveScope(ctx, localScope);
        currentScope = localScope;
    }

    @Override public void exitNested_statements(StopTemplatesParser.Nested_statementsContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterComponent(StopTemplatesParser.ComponentContext ctx) {
        ComponentSymbol componentSymbol = new ComponentSymbol(ctx, currentScope);
        saveScope(ctx, componentSymbol);
        currentScope = componentSymbol;
    }

    @Override public void exitComponent(StopTemplatesParser.ComponentContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterCollection(StopTemplatesParser.CollectionContext ctx) {
        CollectionSymbol collectionSymbol = new CollectionSymbol(ctx, currentScope);
        saveScope(ctx, collectionSymbol);
        currentScope = collectionSymbol;
    }

    @Override public void exitCollection(StopTemplatesParser.CollectionContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterConditional(StopTemplatesParser.ConditionalContext ctx) {
        ConditionalSymbol collectionSymbol = new ConditionalSymbol(ctx, currentScope);
        saveScope(ctx, collectionSymbol);
        currentScope = collectionSymbol;
    }

    @Override public void exitConditional(StopTemplatesParser.ConditionalContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterComponent_parameter(StopTemplatesParser.Component_parameterContext ctx) {
        if( currentScope instanceof ComponentSymbol){
            ComponentSymbol componentSymbol = (ComponentSymbol)currentScope;
            ParameterSymbol parameterSymbol = new ParameterSymbol(ctx);
            componentSymbol.addParameter(ctx.ID().getText(), parameterSymbol);
        }
    }

    void saveScope(ParserRuleContext ctx, Scope s){
        scopes.put(ctx, s);
    }
}
