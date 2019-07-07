package org.stop_lang.tests.runtime.enums;

import org.junit.Assert;
import org.stop_lang.models.Enumeration;
import org.stop_lang.models.EnumerationInstance;
import org.stop_lang.models.StateInstance;
import org.stop_lang.models.Stop;
import org.stop_lang.runtime.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EnumRuntime implements StopRuntimeImplementation<EnumRuntimeBase> {
    private Stop stop;
    private StopRuntime<EnumRuntimeBase> runtime;

    public EnumRuntime() throws IOException {
        stop = Stop.fromFilename("./examples/enum.stop");
        runtime = new StopRuntime<>(stop, this);
    }

    public Stop getStop(){
        return stop;
    }

    public StopRuntime<EnumRuntimeBase> getRuntime(){
        return runtime;
    }

    @Override
    public StateInstance buildStateInstance(EnumRuntimeBase implementationInstance) throws StopRuntimeException {
        return new StateInstance(this.stop.getStates().get(implementationInstance.getName()), implementationInstance);
    }

    @Override
    public EnumRuntimeBase buildImplementationInstance(StateInstance stateInstance) throws StopRuntimeException {
        EnumRuntimeBase base = new EnumRuntimeBase(stateInstance.getState().getName());
        base.putAll(stateInstance.getProperties());
        return base;
    }

    @Override
    public EnumRuntimeBase execute(EnumRuntimeBase implementationInstance, StopRuntimeImplementationExecution<EnumRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        System.out.println("execute! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("sandbox.A")){
            EnumRuntimeBase b = new EnumRuntimeBase("sandbox.B");

            try {
                Enumeration e1 = runtime.getStop().getStates().get("sandbox.B").getEnumerations().get("Z");
                EnumerationInstance ei1 = new EnumerationInstance(e1, "FOUR");
                b.put("e", ei1);

                Enumeration e2 = runtime.getStop().getEnumerations().get("sandbox.Method");
                EnumerationInstance ei2 = new EnumerationInstance(e2, "GET");
                b.put("m", ei2);

                Enumeration e3 = runtime.getStop().getEnumerations().get("sandbox.enums.Boolean");
                EnumerationInstance ei3 = new EnumerationInstance(e3, "YES");
                b.put("boolean", ei3);

                Enumeration e4 = runtime.getStop().getEnumerations().get("sandbox.enums.Status");
                EnumerationInstance ei4 = new EnumerationInstance(e4, "SUCCESS");
                b.put("status", ei4);

                Enumeration e5 = runtime.getStop().getEnumerations().get("sandbox.Residence");
                EnumerationInstance ei5 = new EnumerationInstance(e5, "HOME");
                b.put("r", ei5);

                Enumeration e6 = runtime.getStop().getEnumerations().get("sandbox.enums.produce.Fruit");
                EnumerationInstance ei6 = new EnumerationInstance(e6, "APPLE");
                b.put("fruit", ei6);

                return b;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (implementationInstance.getName().equalsIgnoreCase("sandbox.B")){
            Assert.assertNotNull(implementationInstance.get("e"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("e")).getValue(), "FOUR");
            Assert.assertNotNull(implementationInstance.get("m"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("m")).getValue(), "GET");
            Assert.assertNotNull(implementationInstance.get("boolean"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("boolean")).getValue(), "YES");
            Assert.assertNotNull(implementationInstance.get("status"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("status")).getValue(), "SUCCESS");
            Assert.assertNotNull(implementationInstance.get("r"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("r")).getValue(), "HOME");
            Assert.assertNotNull(implementationInstance.get("fruit"));
            Assert.assertEquals(((EnumerationInstance)implementationInstance.get("fruit")).getValue(), "APPLE");
            return new EnumRuntimeBase("sandbox.C");
        }
        return null;
    }

    @Override
    public Object executeAndReturnValue(EnumRuntimeBase implementationInstance, StopRuntimeImplementationExecution<EnumRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        return implementationInstance.get("status").toString();
    }

    @Override
    public Collection executeAndReturnCollection(EnumRuntimeBase implementationInstance, StopRuntimeImplementationExecution<EnumRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        return null;
    }

    @Override
    public void enqueue(EnumRuntimeBase implementationInstance) {

    }

    @Override
    public void enqueue(EnumRuntimeBase implementationInstance, Integer delayInSeconds) {

    }

    @Override
    public void log(String message) {

    }
}
