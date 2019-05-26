package org.stop_lang.tests.parser;

import org.junit.jupiter.api.Test;
import org.stop_lang.validation.StopValidationException;
import org.stop_lang.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parserTest() throws Exception {
        assertEquals(Validator.validate("./examples/hello-world.stop"), true);
        assertEquals(Validator.validate("./examples/kitchen-sink.stop"), true);
        assertEquals(Validator.validate("./examples/types.stop"), true);
    }

    @Test
    void errorsTest() throws Exception{
        assertEquals(Validator.validate("./examples/errors/validation-error.stop"), false);
        assertEquals(Validator.validate("./examples/errors/validation-async-error.stop"), false);
        assertEquals(Validator.validate("./examples/errors/include.stop"), false);
        assertEquals(Validator.validate("./examples/errors/timeouts.stop"), false);
        assertEquals(Validator.validate("./examples/errors/refs.stop"), false);
    }
}
