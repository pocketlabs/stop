package org.stop_lang.tests.parser;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.stop_lang.validation.IncludePhase;
import org.stop_lang.validation.StopValidationException;
import org.stop_lang.validation.Validator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void parserTest() throws Exception {
        assertEquals(Validator.validate("./examples/hello-world.stop"), true);
        assertEquals(Validator.validate("./examples/kitchen-sink.stop"), true);
        assertEquals(Validator.validate("./examples/types.stop"), true);
        assertEquals(Validator.validate("./examples/references.stop"), true);
        assertEquals(Validator.validate("./examples/package.stop"), true);
        assertEquals(Validator.validate("./examples/enum.stop"), true);
    }

    @Test
    void errorsTest() throws Exception{
        assertEquals(Validator.validate("./examples/errors/validation-error.stop"), false);
        assertEquals(Validator.validate("./examples/errors/validation-async-error.stop"), false);
        assertEquals(Validator.validate("./examples/errors/include.stop"), false);
        assertEquals(Validator.validate("./examples/errors/timeouts.stop"), false);
        assertEquals(Validator.validate("./examples/errors/refs.stop"), false);
    }

    @Test
    void includeTest(){
        List<String> paths = IncludePhase.getIncludePaths();
        Assert.assertTrue(paths.size() > 1);
        Assert.assertEquals(paths.get(0), "./src/main/resources");
    }
}
