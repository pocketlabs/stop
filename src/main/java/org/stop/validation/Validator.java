package org.stop.validation;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stop.parser.StopLexer;
import org.stop.parser.StopParser;

import java.io.IOException;
import java.util.List;

public class Validator {
    public static boolean validate(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return validate(input);
    }

    public static boolean validate(CharStream input) {
        StopLexer l = new StopLexer(input);
        TokenStream tokens = new CommonTokenStream(l);
        StopParser parser = new StopParser(tokens);
        ParserRuleContext tree = parser.file();

        if (tree.exception != null){
            return false;
        }

        ParseTreeWalker walker = new ParseTreeWalker();

        IncludePhase includes = new IncludePhase();
        walker.walk(includes, tree);

        if (handleErrors(includes.errors) ){
            return false;
        }

        DefPhase def = new DefPhase();
        walker.walk(def, tree);

        if (handleErrors(def.errors) ){
            return false;
        }

        RefPhase ref = new RefPhase(def.globals, def.scopes);
        walker.walk(ref, tree);

        if (handleErrors(ref.errors) ){
            return false;
        }

        TransitionPhase transition = new TransitionPhase(def.globals, def.scopes);
        walker.walk(transition, tree);

        if (handleErrors(transition.errors) ){
            return false;
        }

        StopPhase stop = new StopPhase(def.globals, def.scopes);
        walker.walk(stop, tree);

        if (handleErrors(stop.errors) ){
            return false;
        }

        return true;
    }

    private static boolean handleErrors(List<Exception> exceptions){
        if (!exceptions.isEmpty()) {
            for (Exception exception : exceptions){
                System.err.println(exception.getMessage());
            }
            return true;
        }
        return false;
    }
}
