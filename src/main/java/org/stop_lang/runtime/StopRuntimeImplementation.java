package org.stop_lang.runtime;

import org.stop_lang.models.StateInstance;

import java.util.Collection;

public interface StopRuntimeImplementation<T> {
    StateInstance buildStateInstance(T implementationInstance) throws StopRuntimeException;
    T buildImplementationInstance(StateInstance stateInstance) throws StopRuntimeException;
    T execute(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException, StopRuntimeException;
    Object executeAndReturnValue(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException, StopRuntimeException;
    Collection executeAndReturnCollection(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException, StopRuntimeException;
    void enqueue(T implementationInstance);
    void log(String message);
}
