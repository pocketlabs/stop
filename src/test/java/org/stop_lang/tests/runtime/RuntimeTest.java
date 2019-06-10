package org.stop_lang.tests.runtime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.stop_lang.models.StateInstance;
import org.stop_lang.runtime.StopRuntimeException;
import org.stop_lang.tests.runtime.dynamic.DynamicRuntime;
import org.stop_lang.tests.runtime.dynamic.DynamicRuntimeBase;
import org.stop_lang.tests.runtime.helloworld.HelloRuntime;
import org.stop_lang.tests.runtime.helloworld.HelloRuntimeBase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuntimeTest {

    @Test
    public void smoke() throws Exception {
        HelloRuntime runtime = new HelloRuntime();
        HelloRuntimeBase startInstance = new HelloRuntimeBase("A");
        startInstance.put("test1", "hey now");
        HelloRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
        Assert.assertNotNull(runtime.getRuntime().getStop());

        List<StateInstance> orderedStates = runtime.getRuntime().getOrderedStates();
        Assert.assertTrue(orderedStates.size() == 3);
    }

    @Test
    public void timeout() throws Exception {
        HelloRuntime runtime = new HelloRuntime();
        HelloRuntimeBase startInstance = new HelloRuntimeBase("Z");
        HelloRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
        Assert.assertNotNull(runtime.getRuntime().getStop());
    }

    @Test
    public void badStart() throws Exception {
        HelloRuntime runtime = new HelloRuntime();
        HelloRuntimeBase startInstance = new HelloRuntimeBase("Y");
        try {
            HelloRuntimeBase stop = runtime.getRuntime().start(startInstance);
            Assert.fail();
        }catch(StopRuntimeException e){
            // GOod
        }

        HelloRuntime runtime2 = new HelloRuntime();
        HelloRuntimeBase startInstance2 = new HelloRuntimeBase("Iso");
        try {
            HelloRuntimeBase stop2 = runtime2.getRuntime().start(startInstance2);
            Assert.fail();
        }catch(StopRuntimeException e){
            // GOod
        }
    }

    @Test
    public void dynamic() throws Exception {
        DynamicRuntime runtime = new DynamicRuntime();
        DynamicRuntimeBase startInstance = new DynamicRuntimeBase("Begin");
        startInstance.put("v", "test v");
        DynamicRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
        Assert.assertNotNull(runtime.getRuntime().getStop());
        Assert.assertEquals("End", stop.getName());
        Assert.assertNotNull(stop.get("v"));
        Assert.assertNull(stop.get("w"));
        Assert.assertNotNull(stop.get("a"));
        Assert.assertEquals("GetA", stop.get("a"));
        Assert.assertNotNull(stop.get("b"));
        Assert.assertEquals("GetB", stop.get("b"));
        Assert.assertNotNull(stop.get("c"));
        Assert.assertEquals("GetC", stop.get("c"));
        Assert.assertNotNull(stop.get("d"));
        Assert.assertEquals("GetD", stop.get("d"));
    }
}
