package org.stop_lang.tests.runtime.dynamic;

import org.stop_lang.models.StateInstance;
import org.stop_lang.models.Stop;
import org.stop_lang.runtime.*;
import org.stop_lang.tests.runtime.helloworld.HelloRuntimeBase;

import java.io.IOException;
import java.util.Collection;

public class DynamicRuntime implements StopRuntimeImplementation<DynamicRuntimeBase> {
    private Stop stop;
    private StopRuntime<DynamicRuntimeBase> runtime;

    public DynamicRuntime() throws IOException {
        stop = new Stop("./examples/dynamic.stop");
        runtime = new StopRuntime<>(stop, this);
    }

    public StopRuntime<DynamicRuntimeBase> getRuntime(){
        return runtime;
    }

    @Override
    public StateInstance buildStateInstance(DynamicRuntimeBase implementationInstance) throws StopRuntimeException {
        return new StateInstance(this.stop.getStates().get(implementationInstance.getName()), implementationInstance);
    }

    @Override
    public DynamicRuntimeBase buildImplementationInstance(StateInstance stateInstance) throws StopRuntimeException {
        DynamicRuntimeBase base = new DynamicRuntimeBase(stateInstance.getState().getName());
        base.putAll(stateInstance.getProperties());
        return base;
    }

    @Override
    public DynamicRuntimeBase execute(DynamicRuntimeBase implementationInstance, StopRuntimeImplementationExecution<DynamicRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        System.out.println("execute! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("Begin")){
            DynamicRuntimeBase end = new DynamicRuntimeBase("End");
            end.putAll(implementationInstance);
            return end;
        }
        return null;
    }

    @Override
    public Object executeAndReturnValue(DynamicRuntimeBase implementationInstance, StopRuntimeImplementationExecution<DynamicRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        System.out.println("executeAndReturnValue! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("GetA")){
            return "GetA";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetB")){
            return "GetB";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetC")){
            return "GetC";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetD")){
            return "GetD";
        }
        return null;
    }

    @Override
    public Collection executeAndReturnCollection(DynamicRuntimeBase implementationInstance, StopRuntimeImplementationExecution<DynamicRuntimeBase> execution) throws StopRuntimeErrorException, StopRuntimeException {
        return null;
    }

    @Override
    public void enqueue(DynamicRuntimeBase implementationInstance) {

    }

    @Override
    public void enqueue(DynamicRuntimeBase implementationInstance, Integer delayInSeconds) {

    }

    @Override
    public void log(String message) {

    }
}
