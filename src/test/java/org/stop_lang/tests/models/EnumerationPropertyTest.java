package org.stop_lang.tests.models;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.stop_lang.models.*;

public class EnumerationPropertyTest {
    @Test
    public void property() throws Exception{
        CharStream input = CharStreams.fromFileName("./examples/kitchen-sink.stop");
        Stop stop = new Stop(input);
        State requestState = stop.getStates().get("Request");
        Enumeration enumeration = requestState.getEnumerations().get("Method");
        EnumerationProperty enumerationProperty = (EnumerationProperty)requestState.getProperties().get("method");
        Assert.assertEquals(enumeration, enumerationProperty.getEnumeration());
        Assert.assertEquals(enumerationProperty.getClassType(), EnumerationInstance.class);
    }
}
