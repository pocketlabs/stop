package org.stop_lang.tests.templates;

import org.junit.jupiter.api.Test;
import org.stop_lang.models.State;
import org.stop_lang.models.Stop;
import org.stop_lang.templates.models.Template;
import org.stop_lang.templates.validation.Validator;

public class ParserTest {
    @Test
    void parserTest() throws Exception {
        Stop stop = Stop.fromFilename("./examples/templates/templates.stop");
        State templateStop = stop.getStates().get("Posts");
        Validator.validate(stop, templateStop, "./examples/templates/posts.st");
    }
}
