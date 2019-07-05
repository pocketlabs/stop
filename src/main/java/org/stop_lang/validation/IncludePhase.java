package org.stop_lang.validation;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopLexer;
import org.stop_lang.parser.StopParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IncludePhase extends StopBaseListener {
    public List<Exception> errors = new ArrayList<Exception>();

    @Override public void exitInclude(StopParser.IncludeContext ctx) {
        try {
            String filename = ctx.FILENAME().getText().replaceAll("\"", "");
            CharStream input = CharStreams.fromFileName(filename);
            StopLexer l = new StopLexer(input);
            TokenStream tokens = new CommonTokenStream(l);
            StopParser parser = new StopParser(tokens);
            ParserRuleContext tree = parser.file();

            ParseTreeWalker walker = new ParseTreeWalker();

            IncludePhase includes = new IncludePhase();
            walker.walk(includes, tree);

            int indexToInsertAt = 0;

            for (int i = 0; i < ctx.getParent().children.size(); i++){
                ParseTree rootChild = ctx.getParent().getChild(i);
                if (rootChild == ctx){
                    indexToInsertAt = i;
                }
            }

            for(ParseTree child : tree.children){
                ctx.getParent().children.add(indexToInsertAt + 1, child);
            }
        }catch (IOException exception){
            errors.add(exception);
        }
    }
}


