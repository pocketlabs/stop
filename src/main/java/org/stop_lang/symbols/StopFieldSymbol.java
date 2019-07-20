package org.stop_lang.symbols;

import org.antlr.symtab.FieldSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stop_lang.parser.StopParser;

public abstract class StopFieldSymbol extends FieldSymbol {
    protected String typeName;
    protected DynamicModelSymbol dynamicModel;
    protected boolean optional = false;
    protected String packageName;
    protected String fullTypeName;
    protected StopParser.FieldContext context;

    public StopFieldSymbol(StopParser.FieldContext ctx, String typeName, String packageName){
        super(ctx.ID().getText());
        this.typeName = typeName;
        this.packageName = packageName;
        this.context = ctx;

        if (!isReference(typeName) && !isScalar(typeName)) {
            ParseTree p = getRootContext(ctx).getChild(0);
            if (p != null && (p instanceof StopParser.PackageDeclarationContext)) {
                StopParser.PackageDeclarationContext decl = (StopParser.PackageDeclarationContext) p;
                packageName = decl.packageName().getText();
            }

            if (packageName != null) {
                typeName = packageName + "." + typeName;
            }
        }

        fullTypeName = typeName;
    }

    public String getSimpleTypeName(){
        return typeName;
    }

    public String getTypeName(){
        return fullTypeName;
    }

    public void setDynamicModel(DynamicModelSymbol dynamicModelSymbol){
        this.dynamicModel = dynamicModelSymbol;
    }

    public DynamicModelSymbol getDynamicModel() {
        return this.dynamicModel;
    }

    public void setOptional(boolean optional){
        this.optional = optional;
    }

    public boolean isOptional(){
        return this.optional;
    }

    private ParserRuleContext getRootContext(ParserRuleContext ctx){
        if (ctx.getParent() == null){
            return ctx;
        }
        return getRootContext(ctx.getParent());
    }

    protected boolean isReference(String name){
        return name.contains(".");
    }

    protected boolean isScalar(String name){
        return Character.isLowerCase(name.charAt(0));
    }
}
