package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.stop_lang.parser.StopParser;

public class ReturnSymbol extends SymbolWithScope {
    private String packageName;
    private String fullName;
    private StopParser.ModelContext ctx;
    private boolean scalar;

    public ReturnSymbol(StopParser.ModelContext ctx, Scope enclosingScope, String defaultPackageName){
        super(ctx.return_type().getText());
        setScope(enclosingScope);
        this.ctx = ctx;
        packageName = defaultPackageName;

        String name = null;

        if (ctx.return_type().collection() != null) {
            if (ctx.return_type().collection().type() != null) {
                if (ctx.return_type().collection().type().model_type()!=null) {
                    name = ctx.return_type().collection().type().getText();
                    if (!isReference(name)) {
                        if (packageName != null) {
                            name = packageName + "." + name;
                        }
                    }
                }else{
                    scalar = true;
                    name = ctx.return_type().collection().type().scalar_type().getText();
                }
            }
        }else if(ctx.return_type().type() != null){
            if (ctx.return_type().type().model_type()!=null) {
                name = ctx.return_type().type().getText();
                if (!isReference(name)) {
                    if (packageName != null) {
                        name = packageName + "." + name;
                    }
                }
            }else{
                scalar = true;
                name = ctx.return_type().type().scalar_type().getText();
            }
        }

        fullName = name;
    }

    @Override
    public String getName(){
        return fullName;
    }

    public String getPackageName(){
        return packageName;
    }

    public boolean isCollection(){
        return ctx.return_type().collection() != null;
    }

    public boolean isScalar(){
        return scalar;
    }

    private boolean isReference(String name){
        return name.contains(".");
    }
}

