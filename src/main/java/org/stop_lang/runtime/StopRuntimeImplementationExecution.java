package org.stop_lang.runtime;

public interface StopRuntimeImplementationExecution<T> {
    void queue(T implementationInstance) throws StopRuntimeException;
}
