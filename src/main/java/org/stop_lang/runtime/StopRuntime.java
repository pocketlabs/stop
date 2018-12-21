package org.stop_lang.runtime;

import org.stop_lang.models.*;
import org.stop_lang.validation.StopValidationException;

import java.util.*;
import java.util.concurrent.*;

public class StopRuntime<T> implements StopRuntimeImplementationExecution<T> {
    private Stop stop;
    private StopRuntimeImplementation<T> implementation;
    private ExecutorService executor;
    private StateInstance currentStateInstance = null;

    public StopRuntime(Stop stop, StopRuntimeImplementation<T> implementation){
        this.stop = stop;
        this.implementation = implementation;
        this.executor = Executors.newFixedThreadPool(1);
    }

    public Stop getStop(){
        return this.stop;
    }

    public T start(T toImplementationInstance) throws StopRuntimeException, StopValidationException {
        StateInstance to = implementation.buildStateInstance(toImplementationInstance);
        return start(to);
    }

    @Override
    public void queue(T implementationInstance) throws StopRuntimeException, StopValidationException {
        if (currentStateInstance == null){
            throw new StopRuntimeException("No current state instance");
        }

        StateInstance queue = implementation.buildStateInstance(implementationInstance);

        if (queue == null){
            throw new StopRuntimeException("queue state instance must be defined");
        }

        State queueState = currentStateInstance.getState().getEnqueues().get(queue.getState().getName());

        if (queueState == null){
            throw new StopRuntimeException("Could not find queue " + queue.getState().getName());
        }

        if(!queueState.isQueue()){
            throw new StopRuntimeException("Invalid queue state");
        }

        queue.validateProperties(false);

        implementation.enqueue(implementationInstance);
    }

    @Override
    public void log(String message){
        implementation.log(message);
    }

    private T start(StateInstance to) throws StopRuntimeException, StopValidationException {
        if (to == null){
            throw new StopRuntimeException("To state instances must be defined");
        }

        if (!to.getState().isStart() && !to.getState().isQueue()){
            throw new StopRuntimeException("Invalid start state");
        }

        StateInstance resultInstance = execute(to);

        if (resultInstance!=null){
            if (!resultInstance.getState().isStop()){
                throw new StopRuntimeException(resultInstance.getState().getName()  + " is not a stopping state!");
            }
            return implementation.buildImplementationInstance(resultInstance);
        }

        throw new StopRuntimeException("No ending state!");
    }

    private StateInstance execute(StateInstance stateInstance) throws StopRuntimeException, StopValidationException{
        try {
            gatherDynamicProperties(stateInstance);
        }catch(StopRuntimeErrorException errorException){
            StateInstance errorState = errorException.getErrorStateInstance();
            StateInstance contextState = errorException.getContextStateInstance();

            if (errorState == null){
                throw new StopRuntimeException("Error state was undefined in StopRuntimeErrorException during dynamic property gathering");
            }

            if (contextState == null){
                throw new StopRuntimeException("Context state was undefined in StopRuntimeErrorException during dynamic property gathering");
            }

            return transition(contextState, errorState);
        }

        stateInstance.validateProperties();

        currentStateInstance = stateInstance;

        T implementationInstance = implementation.buildImplementationInstance(stateInstance);

        try {
            T nextImplementationInstance = null;
            if (stateInstance.getState() instanceof AsyncState){
                AsyncState asyncState = (AsyncState)stateInstance.getState();
                final StopRuntime<T> self = this;
                Future<T> future = executor.submit(new Callable<T>() {
                    @Override
                    public T call() throws Exception {
                        return implementation.execute(implementationInstance, self);
                    }
                });

                try {
                    nextImplementationInstance = future.get(asyncState.getTimeout(), TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new StopRuntimeException(e.getMessage());
                } catch (ExecutionException e) {
                    if (e.getCause()!=null){
                        if (e.getCause() instanceof StopRuntimeErrorException){
                            StopRuntimeErrorException runtimeErrorException = (StopRuntimeErrorException)e.getCause();
                            throw runtimeErrorException;
                        }
                    }
                    throw new StopRuntimeException(e.getMessage());
                } catch (TimeoutException e) {
                    future.cancel(true);
                    if (asyncState.getTimeoutTransition()!=null){
                        Map<String, Object> props = new HashMap<String, Object>();
                        props.put("timedOutState", stateInstance);
                        StateInstance errorStateInstance = asyncState.getTimeoutTransition().buildInstance(props);
                        return transition(stateInstance, errorStateInstance);
                    }
                    throw new StopRuntimeException(e.getMessage());
                }
            } else {
                nextImplementationInstance = implementation.execute(implementationInstance, this);
            }

            if (nextImplementationInstance != null) {
                StateInstance nextStateInstance = implementation.buildStateInstance(nextImplementationInstance);
                return transition(stateInstance, nextStateInstance);
            } else {
                return stateInstance;
            }
        } catch (StopRuntimeErrorException errorException) {
            StateInstance errorStateInstance = errorException.getErrorStateInstance();
            return transition(stateInstance, errorStateInstance);
        }
    }

    private StateInstance transition(StateInstance from, StateInstance to) throws StopRuntimeException, StopValidationException {
        if (from == null || to == null){
            throw new StopRuntimeException("From and to state instances must be defined");
        }

        from.validateProperties();

        State errorState = from.getState().getErrors().get(to.getState().getName());
        State transitionState = from.getState().getTransitions().get(to.getState().getName());

        if ((errorState == null) && (transitionState == null)){
            throw new StopRuntimeException("Could not find state to transition to called " + to.getState().getName());
        }

        return execute(to);
    }

    private void gatherDynamicProperties(StateInstance to) throws StopRuntimeException, StopValidationException, StopRuntimeErrorException {
        for (Map.Entry<String, Property> propertyEntry : to.getState().getProperties().entrySet()){
            Property property = propertyEntry.getValue();
            if (property != null){
                if (property.getProvider() != null){
                    State providerState = property.getProvider();
                    StateInstance providerStateInstance = mapStateInstancePropertiesToProvider(to, providerState);
                    providerStateInstance.validateProperties();
                    T providerImplementationInstance = implementation.buildImplementationInstance(providerStateInstance);

                    try {
                        Object value = null;

                        if (providerState.isReturnCollection()) {
                            Collection collection = null;

                            if (providerState instanceof AsyncState){
                                AsyncState asyncProviderState = (AsyncState)providerState;
                                final StopRuntime<T> self = this;
                                Future<Collection> future = executor.submit(new Callable<Collection>() {
                                    @Override
                                    public Collection call() throws Exception {
                                        return implementation.executeAndReturnCollection(providerImplementationInstance, self);
                                    }
                                });

                                try {
                                    collection = future.get(asyncProviderState.getTimeout(), TimeUnit.SECONDS);
                                } catch (InterruptedException e) {
                                    throw new StopRuntimeException(e.getMessage());
                                } catch (ExecutionException e) {
                                    Throwable cause = e.getCause();
                                    if (cause instanceof StopRuntimeErrorException){
                                        StopRuntimeErrorException errorException = (StopRuntimeErrorException)cause;
                                        throw new StopRuntimeErrorException(errorException.getErrorStateInstance(), providerStateInstance);
                                    }
                                    throw new StopRuntimeException(e.getMessage());
                                } catch (TimeoutException e) {
                                    future.cancel(true);
                                    if (asyncProviderState.getTimeoutTransition()!=null){
                                        Map<String, Object> props = new HashMap<String, Object>();
                                        props.put("timedOutState", providerStateInstance);
                                        StateInstance errorStateInstance = asyncProviderState.getTimeoutTransition().buildInstance(props);
                                        throw new StopRuntimeErrorException(errorStateInstance);
                                    }
                                    throw new StopRuntimeException(e.getMessage());
                                }
                            }else {
                                collection = implementation.executeAndReturnCollection(providerImplementationInstance, this);
                            }

                            if (providerState.getReturnState() != null) {
                                List<StateInstance> stateInstances = new ArrayList<StateInstance>();
                                for (Object collectionElement : collection) {
                                    stateInstances.add(implementation.buildStateInstance((T) collectionElement));
                                }
                                value = stateInstances;
                            } else {
                                value = collection;
                            }
                        } else {
                            Object returnValue = null;

                            if (providerState instanceof AsyncState){
                                AsyncState asyncProviderState = (AsyncState)providerState;
                                final StopRuntime<T> self = this;
                                Future<Object> future = executor.submit(new Callable<Object>() {
                                    @Override
                                    public Object call() throws Exception {
                                        return implementation.executeAndReturnValue(providerImplementationInstance, self);
                                    }
                                });

                                try {
                                    returnValue = future.get(asyncProviderState.getTimeout(), TimeUnit.SECONDS);
                                } catch (InterruptedException e) {
                                    throw new StopRuntimeException(e.getMessage());
                                } catch (ExecutionException e) {
                                    Throwable cause = e.getCause();
                                    if (cause instanceof StopRuntimeErrorException){
                                        StopRuntimeErrorException errorException = (StopRuntimeErrorException)cause;
                                        throw new StopRuntimeErrorException(errorException.getErrorStateInstance(), providerStateInstance);
                                    }
                                    throw new StopRuntimeException(e.getMessage());
                                } catch (TimeoutException e) {
                                    future.cancel(true);
                                    if (asyncProviderState.getTimeoutTransition()!=null){
                                        Map<String, Object> props = new HashMap<String, Object>();
                                        props.put("timedOutState", providerStateInstance);
                                        StateInstance errorStateInstance = asyncProviderState.getTimeoutTransition().buildInstance(props);
                                        throw new StopRuntimeErrorException(errorStateInstance);
                                    }
                                    throw new StopRuntimeException(e.getMessage());
                                }
                            }else{
                                returnValue = implementation.executeAndReturnValue(providerImplementationInstance, this);
                            }

                            if (returnValue!=null) {
                                if (providerState.getReturnState() != null) {
                                    value = implementation.buildStateInstance((T) returnValue);
                                } else {
                                    value = returnValue;
                                }
                            }
                        }

                        if (value != null) {
                            to.getProperties().put(property.getName(), value);
                        }
                    }catch(StopRuntimeErrorException errorException){
                        throw new StopRuntimeErrorException(errorException.getErrorStateInstance(), providerStateInstance);
                    }
                }
            }
        }

        for ( Map.Entry<String, Object> entry : to.getProperties().entrySet() ){
            Object value = entry.getValue();
            if (value != null){
                if (value instanceof StateInstance){
                    StateInstance propertyStateInstance = (StateInstance)value;
                    gatherDynamicProperties(propertyStateInstance);
                }
            }
        }
    }

    private StateInstance mapStateInstancePropertiesToProvider(StateInstance stateInstance, State providerState){
        Map<String, Object> providerProperties = new HashMap<>();
        for (Map.Entry<String, Object> stateInstanceEntry : stateInstance.getProperties().entrySet()){
            if (providerState.getProperties().containsKey(stateInstanceEntry.getKey())){
                providerProperties.put(stateInstanceEntry.getKey(), stateInstanceEntry.getValue());
            }
        }
        return new StateInstance(providerState, providerProperties);
    }
}
