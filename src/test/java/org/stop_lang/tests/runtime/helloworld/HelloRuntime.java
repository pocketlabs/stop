package org.stop_lang.tests.runtime.helloworld;

import org.stop_lang.models.*;
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
            b.put("query", "yes");

            HelloRuntimeBase d = new HelloRuntimeBase("D");
            HelloRuntimeBase e = new HelloRuntimeBase("E");
            HelloRuntimeBase f = new HelloRuntimeBase("F");

            f.put("name", "F IT");
            try {
                e.put("f", buildStateInstance(f));
                d.put("e", buildStateInstance(e));
                b.put("d", buildStateInstance(d));
            }catch(Exception ex){
                ex.printStackTrace();
            }

            return b;
        }
        if (implementationInstance.getName().equalsIgnoreCase("B")) {
            System.out.println("GOT MY dYNAMIC TEXT: " + implementationInstance.get("dynamicText"));
            HelloRuntimeBase c = new HelloRuntimeBase("C");
            c.put("test3", "test3");
            c.put("h", implementationInstance.get("h"));
            c.put("j", implementationInstance.get("aj"));
            c.put("n", implementationInstance.get("n"));
            return c;
        }
        if (implementationInstance.getName().equalsIgnoreCase("Z")) {
            HelloRuntimeBase y = new HelloRuntimeBase("Y");
            return y;
        }
        return null;
    }

    @Override
    public Object executeAndReturnValue(HelloRuntimeBase implementationInstance, StopRuntimeImplementationExecution<HelloRuntimeBase> execution) throws StopRuntimeErrorException {
        System.out.println("executeAndReturnValue! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("GetDynamicText")) {
            System.out.println(implementationInstance.values());
            return "DYNAMIC TEXT";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetLayout")) {
            return "LAYOUT TEXT";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetConfiguration")) {
            HelloRuntimeBase c = new HelloRuntimeBase("Configuration");
            c.put("host", "http://test.com");
            return c;
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetDownloadURL")) {
            return "http://test.com/download.zip";
        }
        if (implementationInstance.getName().equalsIgnoreCase("Combine")) {
            HelloRuntimeBase c = new HelloRuntimeBase("Wow");
            c.put("name", "now");
            return c;
        }
        if (implementationInstance.getName().equalsIgnoreCase("CombineAgain")) {
            return "combineAgain";
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetH")) {
            HelloRuntimeBase h = new HelloRuntimeBase("H");
            h.put("i", implementationInstance.get("one"));
            return h;
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetJ")) {
            HelloRuntimeBase j = new HelloRuntimeBase("J");
            StateInstance h = (StateInstance)implementationInstance.get("m");
            j.put("k", h.getProperty("i"));
            return j;
        }
        if (implementationInstance.getName().equalsIgnoreCase("GetN")) {
            return implementationInstance.get("k");
        }
        return null;
    }

    @Override
    public Collection executeAndReturnCollection(HelloRuntimeBase implementationInstance, StopRuntimeImplementationExecution<HelloRuntimeBase> execution) throws StopRuntimeErrorException {
        System.out.println("executeAndReturnCollection! " + implementationInstance.getName());
        if (implementationInstance.getName().equalsIgnoreCase("GetPosts")) {
            HelloRuntimeBase post = new HelloRuntimeBase("Post");
            post.put("title", "Hey!");
            post.put("filename", "download.zip");
            List<String> statusValues = new ArrayList<>();
            statusValues.add("DRAFT");
            statusValues.add("PUBLISHED");
            try {
                EnumerationInstance statusInstance = new EnumerationInstance(new Enumeration("Status", statusValues), "PUBLISHED");
                post.put("status", statusInstance);
            }catch(Exception e){
                e.printStackTrace();
            }
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
        if (implementationInstance.getName().equalsIgnoreCase("GetTimedOutPosts")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void enqueue(HelloRuntimeBase implementationInstance) {

    }

    @Override
    public void enqueue(HelloRuntimeBase implementationInstance, Integer delayInSeconds) {

    }

    @Override
    public void log(String message){

    }
}
