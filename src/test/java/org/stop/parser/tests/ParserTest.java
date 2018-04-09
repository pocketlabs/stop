package org.stop.parser.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;
import org.stop.parser.StopLexer;

import org.antlr.v4.runtime.*;
import org.stop.parser.StopParser;
import org.stop.validation.*;

public class ParserTest {

    @Test
    void parserTest() throws Exception {
        assertEquals(Validator.validate("./examples/hello-world.stop"), true);
        assertEquals(Validator.validate("./examples/kitchen-sink.stop"), true);
    }
}
