package org.stop_lang.symbols;

import org.stop_lang.parser.StopParser;

public class CollectionFieldSymbol extends StopFieldSymbol {
    protected boolean state = false;
    public CollectionFieldSymbol(StopParser.FieldContext ctx, String packageName){
        super(ctx, ctx.collection().type().getText(), packageName);
        this.state = ctx.collection().type().model_type() != null;
    }

    public boolean isState() {
        return state;
    }
}
