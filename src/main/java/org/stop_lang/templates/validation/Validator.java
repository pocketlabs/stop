package org.stop_lang.templates.validation;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stop_lang.models.Property;
import org.stop_lang.models.State;
import org.stop_lang.models.Stop;
import org.stop_lang.templates.models.StateTemplateNode;
import org.stop_lang.templates.models.StateTemplateNodeParameterValue;
import org.stop_lang.templates.models.Template;
import org.stop_lang.templates.models.TemplateNode;
import org.stop_lang.templates.parser.StopTemplatesLexer;
import org.stop_lang.templates.parser.StopTemplatesParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Validator {
    public static boolean validate(Stop stop, State templateState, String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return validate(stop, templateState, input);
    }

    public static boolean validate(Stop stop, State templateState, CharStream input) {
        Template template = getTemplate(stop, templateState, input);
        return template != null;
    }

    public static Template getTemplate(Stop stop, State templateState, String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return getTemplate(stop, templateState, input);
    }

    public static Template getTemplate(Stop stop, State templateState, CharStream input) {
        StopTemplatesLexer l = new StopTemplatesLexer(input);
        TokenStream tokens = new CommonTokenStream(l);
        StopTemplatesParser parser = new StopTemplatesParser(tokens);

        ParserRuleContext tree = parser.file();

        if (tree.exception != null){
            return null;
        }

        ParseTreeWalker walker = new ParseTreeWalker();

        DefPhase def = new DefPhase();
        walker.walk(def, tree);

        if (handleErrors(def.errors) ){
            return null;
        }

        StateTemplateNode root = new StateTemplateNode(templateState);
        for(Map.Entry<String, Property> propertyEntry : templateState.getProperties().entrySet()){
            root.addParameter(propertyEntry.getKey(), new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.ID, propertyEntry.getKey()));
        }

        RefPhase ref = new RefPhase(stop, templateState, root, def.globals, def.scopes);
        walker.walk(ref, tree);

        if (handleErrors(ref.errors) ){
            return null;
        }

        Template template = new Template(templateState, root);

        return template;
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
