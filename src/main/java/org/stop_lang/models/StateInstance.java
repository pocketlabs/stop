package org.stop_lang.models;

import java.util.List;
import java.util.Map;

public class StateInstance {
    private State state;
    private Map<String, Object> properties;

    public StateInstance(State state, Map<String, Object> properties) throws Exception {
        this.state = state;
        this.properties = properties;
        validateProperties();
    }

    public State getState(){
        return this.state;
    }

    private void validateProperties() throws Exception {
        Map<String, Property> stateProperties = this.state.getProperties();
        for (Map.Entry<String, Property> entry : stateProperties.entrySet()){
            if (!properties.containsKey(entry.getKey())){
                throw new Exception("Property not found: " + entry.getKey());
            }
            String propertyName = entry.getKey();
            Property property = entry.getValue();
            if (property == null){
                throw new Exception("Property undefined");
            }
            Object value = properties.get(propertyName);
            if (value != null){
                if (property.isCollection()){
                    if (!(value instanceof List)){
                        throw new Exception("Collection must be defined");
                    }else {
                        List valueList = (List)value;
                        for (Object element : valueList){
                            if (!property.getClassType().equals(element.getClass())) {
                                throw new Exception("Invalid value for collection element within " + entry.getKey());
                            }
                            if ( property instanceof StateProperty ){
                                if (!(element instanceof StateInstance)){
                                    throw new Exception("State property requires state instance");
                                }

                                StateInstance stateInstance = (StateInstance)element;
                                StateProperty stateProperty = (StateProperty)property;

                                if ( !stateProperty.getState().equals(stateInstance.getState()) ){
                                    throw new Exception("State instance " + stateInstance.getState().getName() + " doesn't match property " + stateProperty.getState().getName());
                                }
                            }
                            if ( property instanceof EnumerationProperty ){
                                if (!(element instanceof EnumerationInstance)){
                                    throw new Exception("Enumeration property requires enumeration instance");
                                }

                                EnumerationInstance enumerationInstance = (EnumerationInstance)element;
                                EnumerationProperty enumerationProperty = (EnumerationProperty)property;

                                if ( !enumerationProperty.getEnumeration().equals(enumerationInstance.getEnumeration()) ){
                                    throw new Exception("Enumeration instance doesn't match property");
                                }
                            }
                        }
                    }
                }else {
                    if (!property.getClassType().equals(value.getClass())) {
                        throw new Exception("Invalid value for " + entry.getKey());
                    }

                    if ( property instanceof StateProperty ){
                        if (!(value instanceof StateInstance)){
                            throw new Exception("State property requires state instance");
                        }

                        StateInstance stateInstance = (StateInstance)value;
                        StateProperty stateProperty = (StateProperty)property;

                        if ( !stateProperty.getState().equals(stateInstance.getState()) ){
                            throw new Exception("State instance " + stateInstance.getState().getName() + " doesn't match property " + stateProperty.getState().getName());
                        }
                    }
                    if ( property instanceof EnumerationProperty ){
                        if (!(value instanceof EnumerationInstance)){
                            throw new Exception("Enumeration property requires enumeration instance");
                        }

                        EnumerationInstance enumerationInstance = (EnumerationInstance)value;
                        EnumerationProperty enumerationProperty = (EnumerationProperty)property;

                        if ( !enumerationProperty.getEnumeration().equals(enumerationInstance.getEnumeration()) ){
                            throw new Exception("Enumeration instance doesn't match property");
                        }
                    }
                }
            }else{
                throw new Exception("Invalid null value for " + entry.getKey());
            }
        }
    }
}
