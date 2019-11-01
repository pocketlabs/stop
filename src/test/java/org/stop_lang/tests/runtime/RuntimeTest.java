package org.stop_lang.tests.runtime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.stop_lang.models.*;
import org.stop_lang.models.Enumeration;
import org.stop_lang.runtime.StopRuntimeErrorException;
import org.stop_lang.runtime.StopRuntimeException;
import org.stop_lang.runtime.StopRuntimeImplementation;
import org.stop_lang.runtime.StopRuntimeImplementationExecution;
import org.stop_lang.tests.runtime.dynamic.DynamicRuntime;
import org.stop_lang.tests.runtime.dynamic.DynamicRuntimeBase;
import org.stop_lang.tests.runtime.enums.EnumRuntime;
import org.stop_lang.tests.runtime.enums.EnumRuntimeBase;
import org.stop_lang.tests.runtime.helloworld.HelloRuntime;
import org.stop_lang.tests.runtime.helloworld.HelloRuntimeBase;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

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

        Assert.assertNotNull(stop.get("h"));
        StateInstance h = (StateInstance)stop.get("h");
        Assert.assertEquals("F IT", h.getProperty("i"));

        Assert.assertNotNull(stop.get("j"));
        StateInstance j = (StateInstance)stop.get("j");
        Assert.assertEquals("F IT", j.getProperty("k"));

        Assert.assertNotNull(stop.get("n"));
        Assert.assertEquals("F IT", stop.get("n"));

        List<StateInstance> orderedStates = runtime.getRuntime().getOrderedStates();
        Assert.assertTrue(orderedStates.size() == 3);
    }

    @Test
    public void included() throws Exception {
        HelloRuntime runtime = new HelloRuntime();
        HelloRuntimeBase startInstance = new HelloRuntimeBase("IncludeTest");
        runtime.getRuntime().addPackageImplementation("test.models", new StopRuntimeImplementation<StateInstance>() {
            @Override
            public StateInstance buildStateInstance(StateInstance implementationInstance) throws StopRuntimeException {
                return implementationInstance;
            }

            @Override
            public StateInstance buildImplementationInstance(StateInstance stateInstance) throws StopRuntimeException {
                return stateInstance;
            }

            @Override
            public StateInstance execute(StateInstance implementationInstance, StopRuntimeImplementationExecution<StateInstance> execution) throws StopRuntimeErrorException, StopRuntimeException {
                if (implementationInstance.getState().getName().equalsIgnoreCase("test.models.D")){
                    Map<String, Object> props = new HashMap<String, Object>();
                    props.put("wow", "now");
                    StateInstance c = new StateInstance(runtime.getRuntime().getStop().getStates().get("test.models.C"), props);
                    return c;
                }
                Assert.assertEquals("now", implementationInstance.getProperties().get("wow"));
                Assert.assertEquals("cvalue", implementationInstance.getProperties().get("cvalue"));
                Assert.assertEquals(3, ((Collection<String>)implementationInstance.getProperties().get("dcollection")).size());
                return null;
            }

            @Override
            public Object executeAndReturnValue(StateInstance implementationInstance, StopRuntimeImplementationExecution<StateInstance> execution) throws StopRuntimeErrorException, StopRuntimeException {
                if ( implementationInstance.getState().getName().equalsIgnoreCase("test.models.GetCValue")){
                    return "cvalue";
                }
                return null;
            }

            @Override
            public Collection executeAndReturnCollection(StateInstance implementationInstance, StopRuntimeImplementationExecution<StateInstance> execution) throws StopRuntimeErrorException, StopRuntimeException {
                if ( implementationInstance.getState().getName().equalsIgnoreCase("test.models.GetDCollection")){
                    List<String> collection = new ArrayList<>();
                    collection.add("one");
                    collection.add("two");
                    collection.add("three");
                    return collection;
                }
                return null;
            }

            @Override
            public void enqueue(StateInstance implementationInstance) {

            }

            @Override
            public void enqueue(StateInstance implementationInstance, Integer delayInSeconds) {

            }

            @Override
            public void log(String message) {

            }
        });
        HelloRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
        Assert.assertNotNull(runtime.getRuntime().getStop());
        Assert.assertEquals(stop.getName(), "test.models.C");
        Assert.assertEquals(stop.get("wow"), "now");
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
        Assert.assertNotNull(stop.get("e"));
        StateInstance e = (StateInstance)stop.get("e");
        Assert.assertNotNull(e.getProperties().get("f"));
        for (StateInstance f : (List<StateInstance>)e.getProperties().get("f")){
            Assert.assertEquals(f.getProperty("g"), "g");
            Assert.assertEquals(f.getProperty("h"), "GetH");
        }
        String optionalTest = (String)stop.get("optional_test");
        Assert.assertNotNull(optionalTest);
        Assert.assertEquals("OptionalGetTestAlphaOne_null_OptionalGetTestAlphaTwo_null_null", optionalTest);
    }

    @Test
    public void dynamicOptionals() throws Exception {
        DynamicRuntime runtime = new DynamicRuntime();
        DynamicRuntimeBase startInstance = new DynamicRuntimeBase("Begin");
        startInstance.put("v", "test v");
        startInstance.put("w", "optional w");
        DynamicRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
        Assert.assertNotNull(runtime.getRuntime().getStop());
        Assert.assertEquals("End", stop.getName());
        Assert.assertNotNull(stop.get("v"));
        Assert.assertNotNull(stop.get("w"));
        Assert.assertNotNull(stop.get("a"));
        Assert.assertEquals("GetA", stop.get("a"));
        Assert.assertNotNull(stop.get("b"));
        Assert.assertEquals("GetB", stop.get("b"));
        Assert.assertNotNull(stop.get("c"));
        Assert.assertEquals("GetC", stop.get("c"));
        Assert.assertNotNull(stop.get("d"));
        Assert.assertEquals("GetD", stop.get("d"));
        Assert.assertNotNull(stop.get("e"));
        StateInstance e = (StateInstance)stop.get("e");
        Assert.assertNotNull(e.getProperties().get("f"));
        for (StateInstance f : (List<StateInstance>)e.getProperties().get("f")){
            Assert.assertEquals(f.getProperty("g"), "g");
            Assert.assertEquals(f.getProperty("h"), "GetH");
        }
        String optionalTest = (String)stop.get("optional_test");
        Assert.assertNotNull(optionalTest);
        Assert.assertEquals("OptionalGetTestAlphaOne_optional w_OptionalGetTestAlphaTwo_optional w_OptionalGetTestAlphaThree", optionalTest);
    }

    @Test
    public void enums() throws Exception {
        EnumRuntime runtime = new EnumRuntime();
        EnumRuntimeBase startInstance = new EnumRuntimeBase("sandbox.A");
        Enumeration e = runtime.getStop().getStates().get("sandbox.A").getEnumerations().get("Y");
        EnumerationInstance ei = new EnumerationInstance(e, "TWO");
        startInstance.put("f", ei);
        EnumRuntimeBase stop = runtime.getRuntime().start(startInstance);
        Assert.assertNotNull(stop);
    }

    @Test
    public void getOrderedPropertiesForState() throws IOException {
        HelloRuntime runtime = new HelloRuntime();
        State b = runtime.getRuntime().getStop().getStates().get("B");
        List<Property> properties = runtime.getRuntime().getOrderedDynamicPropertiesForState(b);
        Assert.assertEquals(properties.size(), 9);
        int aj = -1;
        int n = -1;
        int h = -1;
        int i = 0;
        for (Property p : properties){
            Assert.assertNotNull(p.getProvider());
            if (p.getName().equalsIgnoreCase("aj")){
                aj = i;
            }
            if (p.getName().equalsIgnoreCase("n")){
                n = i;
            }
            if (p.getName().equalsIgnoreCase("h")){
                h = i;
            }
            i++;
        }
        Assert.assertTrue(aj < n);
        Assert.assertTrue(h < aj);
    }
}
