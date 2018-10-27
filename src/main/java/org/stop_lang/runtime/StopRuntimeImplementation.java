package org.stop_lang.runtime;

import org.stop_lang.models.StateInstance;

import java.util.Collection;

public interface StopRuntimeImplementation<T> {
    StateInstance buildStateInstance(T implementationInstance);
    T buildImplementationInstance(StateInstance stateInstance);
    T execute(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException;
    Object executeAndReturnValue(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException;
    Collection executeAndReturnCollection(T implementationInstance, StopRuntimeImplementationExecution<T> execution) throws StopRuntimeErrorException;
}
