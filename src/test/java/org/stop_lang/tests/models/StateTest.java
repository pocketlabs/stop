package org.stop_lang.tests.models;

import org.junit.Assert;
import org.junit.Test;
import org.stop_lang.models.Property;
import org.stop_lang.models.State;

import java.util.TreeMap;

public class StateTest {
    @Test
    public void state(){
        State s = new State("Test");
        Assert.assertFalse(s.equals(new String()));
        Assert.assertTrue(s.isSync());
        Assert.assertEquals(s.getType(), State.StateType.SYNC);
        Assert.assertFalse(s.isAsync());

        Assert.assertFalse(s.hasReturnType());

        s.setReturn(Property.PropertyType.STRING, null, false);
        Assert.assertEquals(s.getReturnType(), Property.PropertyType.STRING);

        Assert.assertTrue(s.hasReturnType());
        Assert.assertFalse(s.isQueue());

        State q = new State("Queue");
        TreeMap<String, State> enqueues = new TreeMap<String, State>();
        enqueues.put("Queue", q);
        s.setEnqueues(enqueues);

        Assert.assertEquals(s.getEnqueues().get("Queue"), q);
    }
}
