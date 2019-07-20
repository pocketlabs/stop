package org.stop_lang.symbols;

import org.stop_lang.parser.StopParser;

public class ModelFieldSymbol extends StopFieldSymbol {
    public ModelFieldSymbol(StopParser.FieldContext ctx, String packageName){
        super(ctx, ctx.type().model_type().getText(), packageName);
    }
}
