package org.stop_lang.symbols;

import org.antlr.symtab.Scope;
import org.antlr.symtab.SymbolWithScope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stop_lang.parser.StopParser;

import java.util.HashMap;
import java.util.Map;

public class DynamicModelSymbol extends SymbolWithScope {
    private String packageName;
    private String fullName;
    private Map<String, String> sourceMapping = null;

    public DynamicModelSymbol(StopParser.FieldContext ctx, Scope enclosingScope, String defaultPackageName){
        super(ctx.async_source().model_type().getText());
        setScope(enclosingScope);
        packageName = defaultPackageName;
        String name = ctx.async_source().model_type().getText();

        if (!isReference(name)) {
            ParseTree p = getRootContext(ctx).getChild(0);
            if (p != null && (p instanceof StopParser.PackageDeclarationContext)) {
                StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext) p;
                packageName = decl.packageName().getText();
            }

            if (packageName != null) {
                name = packageName + "." + name;
            }
        }

        if (ctx.async_source().async_source_mapping() != null){
            Map<String, String> asyncSourceMapping = new HashMap<String, String>();
            for (StopParser.Async_source_mapping_parameterContext parameterContext :
                    ctx.async_source().async_source_mapping().async_source_mapping_parameter()){
                asyncSourceMapping.put(parameterContext.ID().getText(), parameterContext.async_source_mapping_parameter_rename().getText());
            }
            sourceMapping = asyncSourceMapping;
        }

        fullName = name;
    }

    @Override
    public String getName(){
        return fullName;
    }

    public String getFullName(){
        return fullName;
    }

    public String getPackageName(){
        return packageName;
    }

    public Map<String, String> getSourceMapping(){
        return sourceMapping;
    }

    private boolean isReference(String name){
        return name.contains(".");
    }

    private ParserRuleContext getRootContext(ParserRuleContext ctx){
        if (ctx.getParent() == null){
            return ctx;
        }
        return getRootContext(ctx.getParent());
    }
}
