package org.stop_lang.parser.tests;

import org.junit.jupiter.api.Test;
import org.stop_lang.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parserTest() throws Exception {
        assertEquals(Validator.validate("./examples/hello-world.stop"), true);
        assertEquals(Validator.validate("./examples/kitchen-sink.stop"), true);
    }
}
