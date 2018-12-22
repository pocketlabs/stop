package org.stop_lang.runtime.tests.helloworld;

import org.stop_lang.models.StateInstance;
import org.stop_lang.models.Stop;
import org.stop_lang.runtime.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HelloRuntime implements StopRuntimeImplementation<HelloRuntimeBase> {
    private Stop stop;
    private StopRuntime<HelloRuntimeBase> runtime;

    public HelloRuntime() throws IOException {
        stop = new Stop("./examples/runtime.stop");
        runtime = new StopRuntime<>(stop, this);
    }

    public StopRuntime<HelloRuntimeBase> getRuntime(){
        return runtime;
    }

    @Override
    public StateInstance buildStateInstance(HelloRuntimeBase implementationInstance) throws StopRuntimeException{
        return new StateInstance(this.stop.getStates().get(implementationInstance.getName()), implementationInstance);
    }

    @Override
    public HelloRuntimeBase buildImplementationInstance(StateInstance stateInstance)  throws StopRuntimeException{
        HelloRuntimeBase base = new HelloRuntimeBase(stateInstance.getState().getName());
        base.putAll(stateInstance.getProperties());
        return base;
    }

    @Override
    public HelloRuntimeBase execute(HelloRuntimeBase implementationInstance, StopRuntimeImplementationExecution<HelloRuntimeBase> execution) throws StopRuntimeErrorException {
        System.out.println("execute! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("A")){
            HelloRuntimeBase b = new HelloRuntimeBase("B");
            b.put("test2", "test2");
            return b;
        }
        if (implementationInstance.getName().equalsIgnoreCase("B")) {
            System.out.println("GOT MY dYNAMIC TEXT: " + implementationInstance.get("dynamicText"));
            HelloRuntimeBase c = new HelloRuntimeBase("C");
            c.put("test3", "test3");
            return c;
        }
        return null;
    }

    @Override
    public Object executeAndReturnValue(HelloRuntimeBase implementationInstance, StopRuntimeImplementationExecution<HelloRuntimeBase> execution) throws StopRuntimeErrorException {
        System.out.println("executeAndReturnValue! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("GetDynamicText")) {
            return "DYNAMIC TEXT";
        }
        return null;
    }

    @Override
    public Collection executeAndReturnCollection(HelloRuntimeBase implementationInstance, StopRuntimeImplementationExecution<HelloRuntimeBase> execution) throws StopRuntimeErrorException {
        System.out.println("executeAndReturnCollection! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("GetPosts")) {
//            HelloRuntimeBase databaseError = new HelloRuntimeBase("DatabaseError");
//            StateInstance err = this.buildStateInstance(databaseError);
//            throw new StopRuntimeErrorException(err);
            
            HelloRuntimeBase post = new HelloRuntimeBase("Post");
            post.put("title", "Hey!");
            List<HelloRuntimeBase> posts = new ArrayList<>();
            posts.add(post);
            return posts;
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetFilteredPosts")) {
            HelloRuntimeBase post = new HelloRuntimeBase("Post");
            post.put("title", "Hey! Filtered! Post!");
            List<HelloRuntimeBase> posts = new ArrayList<>();
            posts.add(post);
            return posts;
        }
        return null;
    }

    @Override
    public void enqueue(HelloRuntimeBase implementationInstance) {

    }

    @Override
    public void log(String message){

    }
}
