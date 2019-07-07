package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stop_lang.parser.StopParser;

import java.util.ArrayList;
import java.util.List;

public class EnumSymbol extends SymbolWithScope {
    private List<String> values = new ArrayList<String>();
    private String fullName;
    private String packageName;
    private boolean canonical;

    public EnumSymbol(StopParser.EnumerationContext ctx, Scope enclosingScope, String defaultPackageName){
        super(ctx.enum_type().MODEL_TYPE().getText());
        setScope(enclosingScope);

        String name = ctx.enum_type().MODEL_TYPE().getText();
        fullName = name;

        if (ctx.getParent() instanceof StopParser.FileContext) {
            canonical = true;
            ParseTree p = ctx.getParent().getChild(0);
            if (p != null && (p instanceof StopParser.PackageDeclarationContext)) {
                StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext) p;
                packageName = decl.packageName().getText();
                fullName = packageName + "." + name;
            } else if (defaultPackageName!=null){
                packageName = defaultPackageName;
                fullName = packageName + "." + name;
            }
        }else{
            StopParser.ModelContext modelContext = (StopParser.ModelContext)ctx.getParent().getParent().getParent();
            String modelName = modelContext.MODEL_TYPE().getText();

            name = modelName + "." + ctx.enum_type().MODEL_TYPE().getText();

            ParseTree p = ctx.getParent().getParent().getParent().getParent().getChild(0);
            if (p!=null && (p instanceof StopParser.PackageDeclarationContext)){
                StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext)p;
                packageName = decl.packageName().getText();
                fullName = packageName + "." + name;
            }
        }


    }

    public List<String> getValues(){
        return values;
    }

    public void addValue(String t){
        values.add(t);
    }

    public String getName(){
        if (canonical){
            return fullName;
        }
        return super.getName();
    }

    public String getFullName(){
        return fullName;
    }
}
