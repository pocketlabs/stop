package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stop_lang.parser.StopParser;

public class TimeoutSymbol  extends SymbolWithScope {
    private String packageName;
    private String fullName;
    private StopParser.TimeoutContext ctx;
    private int seconds;

    public TimeoutSymbol(StopParser.TimeoutContext ctx, Scope enclosingScope, String defaultPackageName){
        super(ctx.transition().model_type().getText());
        setScope(enclosingScope);
        this.ctx = ctx;
        seconds =  Integer.parseInt(ctx.NUMBER().getText());
        packageName = defaultPackageName;
        String name = ctx.transition().model_type().getText();

        ParseTree p = getRootContext(ctx).getChild(0);
        if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
            StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
            packageName = decl.packageName().getText();
        }

        if (packageName!=null){
            name = packageName + "." + name;
        }

        fullName = name;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPackageName(){
        return packageName;
    }

    public StopParser.TimeoutContext getContext(){
        return ctx;
    }

    public Integer getTimeoutSeconds(){
        return seconds;
    }

    private ParserRuleContext getRootContext(ParserRuleContext ctx){
        if (ctx.getParent() == null){
            return ctx;
        }
        return getRootContext(ctx.getParent());
    }
}

