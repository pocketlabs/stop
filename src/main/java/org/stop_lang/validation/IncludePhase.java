package org.stop_lang.validation;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stop_lang.parser.StopBaseListener;
import org.stop_lang.parser.StopLexer;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.ModelSymbol;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IncludePhase extends StopBaseListener {
    public List<Exception> errors = new ArrayList<Exception>();

    @Override public void exitInclude(StopParser.IncludeContext ctx) {
        String filename = ctx.FILENAME().getText().replaceAll("\"", "");
        CharStream input = getInput(filename);

        if (input == null){
            errors.add(new IOException("Couldn't find include: "+filename));
            return;
        }

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
            if (!(child instanceof StopParser.PackageDeclarationContext)){
                ctx.getParent().children.add(indexToInsertAt + 1, child);
            }
        }
    }

    private CharStream getInput(String filename){
        try {
            CharStream input = CharStreams.fromFileName(filename);
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String path : getIncludePaths()) {
            String f = path + File.separator + filename;
            try {
                CharStream input = CharStreams.fromFileName(f);
                return input;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static List<String> getIncludePaths(){
        List<String> includePaths = new ArrayList<>();

        String includePathsString = System.getenv().get("STOP_INCLUDE_PATHS");
        if (includePaths!=null){
            String[] paths = includePathsString.split("\\s*,\\s*");
            for(int i = 0; i < paths.length; i++){
                String path = paths[i];
                includePaths.add(path);
            }
        }

        return includePaths;
    }
}


