package org.stop_lang.models;

import org.stop_lang.validation.StopValidationException;

import java.util.List;
import java.util.Map;

public class StateInstance {
    private State state;
    private Map<String, Object> properties;

    public StateInstance(State state, Map<String, Object> properties) {
        this.state = state;
        this.properties = properties;
    }

    public State getState(){
        return this.state;
    }

    public Object getProperty(String name){
        return properties.get(name);
    }

    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public void validateProperties() throws StopValidationException {
        validateProperties(true);
    }

    public void validateProperties(boolean validateDynamicProperties) throws StopValidationException {
        Map<String, Property> stateProperties = this.state.getProperties();
        for (Map.Entry<String, Property> entry : stateProperties.entrySet()){
            if (!properties.containsKey(entry.getKey())){
                if (!validateDynamicProperties && (entry.getValue().getProvider()!=null)){
                    continue;
                }
                if (entry.getValue().isOptional()){
                    continue;
                }else {
                    throw new StopValidationException("Property not found: " + entry.getKey());
                }
            }
            String propertyName = entry.getKey();
            Property property = entry.getValue();
            if (property == null){
                throw new StopValidationException("Property undefined");
            }
            Object value = properties.get(propertyName);
            if (value != null){
                if (property.isCollection()){
                    if (!(value instanceof List)){
                        throw new StopValidationException("Collection must be defined");
                    }else {
                        List valueList = (List)value;
                        for (Object element : valueList){
                            if (!property.getClassType().equals(element.getClass())) {
                                throw new StopValidationException("Invalid type "+element.getClass().getName()+" was expecting " +property.getClassType().getName()+ " for collection element within " + entry.getKey());
                            }
                            if ( property instanceof StateProperty ){
                                if (!(element instanceof StateInstance)){
                                    throw new StopValidationException("State property requires state instance");
                                }

                                StateInstance stateInstance = (StateInstance)element;
                                StateProperty stateProperty = (StateProperty)property;

                                if ( !stateProperty.getState().equals(stateInstance.getState()) ){
                                    throw new StopValidationException("State instance " + stateInstance.getState().getName() + " doesn't match property " + stateProperty.getState().getName());
                                }
                            }
                            if ( property instanceof EnumerationProperty ){
                                if (!(element instanceof EnumerationInstance)){
                                    throw new StopValidationException("Enumeration property requires enumeration instance");
                                }

                                EnumerationInstance enumerationInstance = (EnumerationInstance)element;
                                EnumerationProperty enumerationProperty = (EnumerationProperty)property;

                                if ( !enumerationProperty.getEnumeration().equals(enumerationInstance.getEnumeration()) ){
                                    throw new StopValidationException("Enumeration instance doesn't match property");
                                }
                            }
                        }
                    }
                }else {
                    if (!property.getClassType().equals(value.getClass())) {
                        throw new StopValidationException("Invalid value for " + entry.getKey());
                    }

                    if ( property instanceof StateProperty ){
                        if (!(value instanceof StateInstance)){
                            throw new StopValidationException("State property requires state instance");
                        }

                        StateInstance stateInstance = (StateInstance)value;
                        StateProperty stateProperty = (StateProperty)property;

                        if ( !stateProperty.getState().equals(stateInstance.getState()) ){
                            throw new StopValidationException("State instance " + stateInstance.getState().getName() + " doesn't match property " + stateProperty.getState().getName());
                        }
                    }
                    if ( property instanceof EnumerationProperty ){
                        if (!(value instanceof EnumerationInstance)){
                            throw new StopValidationException("Enumeration property requires enumeration instance");
                        }

                        EnumerationInstance enumerationInstance = (EnumerationInstance)value;
                        EnumerationProperty enumerationProperty = (EnumerationProperty)property;

                        if ( !enumerationProperty.getEnumeration().equals(enumerationInstance.getEnumeration()) ){
                            throw new StopValidationException("Enumeration instance doesn't match property");
                        }
                    }
                }
            }else{
                throw new StopValidationException("Invalid null value for " + entry.getKey());
            }
        }
    }
}
