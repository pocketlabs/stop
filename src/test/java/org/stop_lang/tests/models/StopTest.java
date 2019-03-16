package org.stop_lang.tests.models;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.stop_lang.models.State;
import org.stop_lang.models.Stop;

import java.util.Set;

public class StopTest {
    @Test
    public void stop() throws Exception{
        CharStream input = CharStreams.fromFileName("./examples/kitchen-sink.stop");
        Stop stop = new Stop(input);
        Set<State> startStates = stop.getStartStates();
        Assert.assertTrue(startStates.size() == 1);
        Assert.assertEquals(startStates.iterator().next().getName(), "Router");
    }
}
