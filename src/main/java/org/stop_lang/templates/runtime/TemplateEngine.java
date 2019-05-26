package org.stop_lang.templates.runtime;

import org.stop_lang.models.*;
import org.stop_lang.templates.models.*;
import org.stop_lang.validation.StopValidationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TemplateEngine<T> {
    private Template template;
    private TemplateEngineImplementation<T> implementation;

    public TemplateEngine(TemplateEngineImplementation<T> implementation, Template template){
        this.template = template;
        this.implementation = implementation;
    }

    public T render(StateInstance templateStateInstance) throws TemplateEngineException, StopValidationException {
        return render(templateStateInstance, null);
    }

    public T render(StateInstance templateStateInstance, Collection<T> children) throws TemplateEngineException, StopValidationException {
        if (!templateStateInstance.getState().equals(template.getTemplateState())){
            throw new TemplateEngineException("State instance does not map to template state");
        }

        templateStateInstance.validateProperties();

        Map<String, Object> context = templateStateInstance.getProperties();

        if (children!=null) {
            context.put("children", children);
        }


        TemplateNode root = template.getRoot();

        Collection<T> childrenCollection = collect(0, root.getChildren(), context);

        return this.implementation.renderTemplateChildren(childrenCollection);
    }

    private Collection<T> collect( int level, Collection<TemplateNode> nodes, Map<String,Object> context) throws TemplateEngineException, StopValidationException {
        Collection<T> children = new ArrayList<>();

        for (TemplateNode child : nodes) {
            Collection<T> visited = visit(level, child, context);
            if (visited != null) {
                children.addAll(visited);
            }
        }

        return children;
    }

    private Collection<T> visit(int level, TemplateNode node, Map<String, Object> context) throws TemplateEngineException, StopValidationException{
        if (node instanceof StateTemplateNode) {
            StateTemplateNode stateTemplateNode = (StateTemplateNode) node;
            StateInstance stateInstance = mapContextToStateInstance(stateTemplateNode.getState(), context, stateTemplateNode);

            Collection<T> children = collect(level+1, node.getChildren(), context);

            Collection<T> retValues = new ArrayList<>();
            retValues.add(implementation.executeTemplate(stateInstance, children));
            return retValues;
        } else if (node instanceof  CollectionTemplateNode){
            CollectionTemplateNode collectionTemplateNode = (CollectionTemplateNode)node;
            if (collectionTemplateNode.getProperties()!=null){
                Object value = null;
                if (!collectionTemplateNode.getProperties().isEmpty()) {
                    Property rootProperty = collectionTemplateNode.getProperties().get(0);
                    Object rootObject = context.get(rootProperty.getName());
                    if ((rootObject!=null) && (rootObject instanceof StateInstance)){
                        StateInstance stateInstance = (StateInstance)rootObject;
                        int nodeIndex = 1;
                        while (nodeIndex < collectionTemplateNode.getProperties().size()) {
                            Property nodeProperty = collectionTemplateNode.getProperties().get(nodeIndex);
                            Object nodeValue = stateInstance.getProperties().get(nodeProperty.getName());
                            if (nodeValue!=null) {
                                if (nodeIndex == (collectionTemplateNode.getProperties().size()-1)){
                                    value = nodeValue;
                                    break;
                                }else if (nodeValue instanceof StateInstance) {
                                    stateInstance = (StateInstance)nodeValue;
                                    nodeIndex++;
                                } else {
                                    break;
                                }
                            }else {
                                break;
                            }
                        }
                    }
                }
                if (!collectionTemplateNode.getEmpty() && (value != null) && (value instanceof Collection) && !((Collection) value).isEmpty()) {
                    Collection values = (Collection) value;
                    Collection<T> retValues = new ArrayList<>();
                    for (Object o : values) {
                        if (o instanceof StateInstance) {
                            StateInstance si = (StateInstance) o;
                            Map<String, Object> valueContext = new HashMap<String, Object>();
                            valueContext.putAll(context);
                            valueContext.putAll(si.getProperties());
                            valueContext.put(collectionTemplateNode.getProperty().getName() + "_item", si);

                            retValues.addAll(collect(level, node.getChildren(), valueContext));
                        } else {
                            Map<String, Object> valueContext = new HashMap<String, Object>();
                            valueContext.putAll(context);
                            valueContext.put(collectionTemplateNode.getProperty().getName() + "_item", o);
                            retValues.addAll(collect(level, node.getChildren(), valueContext));
                        }
                    }
                    return retValues;
                } else if (collectionTemplateNode.getEmpty() && (value != null) && (value instanceof Collection) && ((Collection) value).isEmpty()) {
                    return collect(level, node.getChildren(), context);
                } else if (collectionTemplateNode.getEmpty() && (value==null)){
                    return collect(level, node.getChildren(), context);
                }
            }else {
                if (context.containsKey(collectionTemplateNode.getProperty().getName())) {
                    Object value = context.get(collectionTemplateNode.getProperty().getName());
                    if (!collectionTemplateNode.getEmpty() && (value != null) && (value instanceof Collection) && !((Collection) value).isEmpty()) {
                        Collection values = (Collection) value;
                        Collection<T> retValues = new ArrayList<>();
                        for (Object o : values) {
                            if (o instanceof StateInstance) {
                                StateInstance si = (StateInstance) o;
                                Map<String, Object> valueContext = new HashMap<String, Object>();
                                valueContext.putAll(context);
                                valueContext.putAll(si.getProperties());
                                valueContext.put(collectionTemplateNode.getProperty().getName() + "_item", si);

                                retValues.addAll(collect(level, node.getChildren(), valueContext));
                            } else {
                                Map<String, Object> valueContext = new HashMap<String, Object>();
                                valueContext.putAll(context);
                                valueContext.put(collectionTemplateNode.getProperty().getName() + "_item", o);

                                retValues.addAll(collect(level, node.getChildren(), valueContext));
                            }
                        }
                        return  retValues;
                    } else if (collectionTemplateNode.getEmpty() && (value != null) && (value instanceof Collection) && ((Collection) value).isEmpty()) {
                        return collect(level, node.getChildren(), context);
                    }
                } else if (collectionTemplateNode.getEmpty() && !context.containsKey(collectionTemplateNode.getProperty().getName())) {
                    return collect(level, node.getChildren(), context);
                }
            }
        }else if (node instanceof ConditionalTemplateNode) {
            ConditionalTemplateNode conditionalTemplateNode = (ConditionalTemplateNode)node;
            if (conditionalTemplateNode.getProperties()!=null){
                Object value = null;
                if (!conditionalTemplateNode.getProperties().isEmpty()) {
                    Property rootProperty = conditionalTemplateNode.getProperties().get(0);
                    Object rootObject = context.get(rootProperty.getName());
                    if ((rootObject!=null) && (rootObject instanceof StateInstance)){
                        StateInstance stateInstance = (StateInstance)rootObject;
                        int nodeIndex = 1;
                        while (nodeIndex < conditionalTemplateNode.getProperties().size()) {
                            Property nodeProperty = conditionalTemplateNode.getProperties().get(nodeIndex);
                            Object nodeValue = stateInstance.getProperties().get(nodeProperty.getName());
                            if (nodeValue!=null) {
                                if (nodeIndex == (conditionalTemplateNode.getProperties().size()-1)){
                                    value = nodeValue;
                                    break;
                                }else if (nodeValue instanceof StateInstance) {
                                    stateInstance = (StateInstance)nodeValue;
                                    nodeIndex++;
                                } else {
                                    break;
                                }
                            }else {
                                break;
                            }
                        }
                    }
                }
                if (!conditionalTemplateNode.getEmpty() && (value != null) && (value != Boolean.FALSE) && !((value instanceof Collection) && ((Collection)value).isEmpty())) {
                    Map<String, Object> valueContext = new HashMap<String, Object>();
                    valueContext.putAll(context);
                    if (value instanceof StateInstance) {
                        StateInstance si = (StateInstance) value;
                        valueContext.putAll(si.getProperties());
                    }
                    return collect(level, node.getChildren(), valueContext);
                } else if (conditionalTemplateNode.getEmpty() && ((value == null) || ((value instanceof Boolean) && (value == Boolean.FALSE)) || ((value instanceof Collection) && ((Collection)value).isEmpty()) )) {
                    return collect(level, node.getChildren(), context);
                }
            } else {
                if (context.containsKey(conditionalTemplateNode.getProperty().getName())) {
                    Object value = context.get(conditionalTemplateNode.getProperty().getName());
                    if (!conditionalTemplateNode.getEmpty() && (value != null) && (value != Boolean.FALSE) && !((value instanceof Collection) && ((Collection)value).isEmpty()) ) {
                        Map<String, Object> valueContext = new HashMap<String, Object>();
                        valueContext.putAll(context);
                        if (value instanceof StateInstance) {
                            StateInstance si = (StateInstance) value;
                            valueContext.putAll(si.getProperties());
                        }
                        return collect(level, node.getChildren(), valueContext);
                    } else if (conditionalTemplateNode.getEmpty() && ((value == null) || ((value instanceof Boolean) && (value == Boolean.FALSE)) || ((value instanceof Collection) && ((Collection)value).isEmpty()) )) {
                        return collect(level, node.getChildren(), context);
                    }
                } else if (conditionalTemplateNode.getEmpty() && !context.containsKey(conditionalTemplateNode.getProperty().getName())) {
                    return collect(level, node.getChildren(), context);
                }
            }
        }
        return null;
    }

    private StateInstance mapContextToStateInstance(State state, Map<String, Object> context, StateTemplateNode node) throws StopValidationException{
        Map<String, Object> props = new HashMap<>();

        for(Map.Entry<String, StateTemplateNodeParameterValue> entry : node.getParameters().entrySet()){
            StateTemplateNodeParameterValue value = entry.getValue();

            if (value.getType() == StateTemplateNodeParameterValue.Type.COLLECTION) {
                if ( value instanceof StopTemplateNodeParameterCollection) {
                    StopTemplateNodeParameterCollection parameterCollection = (StopTemplateNodeParameterCollection)value;
                    Collection values = new ArrayList();
                    for ( StateTemplateNodeParameterValue parameterValue : parameterCollection.getValues() ){
                        Object object = mapParameterValue(state, context, entry.getKey(), parameterValue);
                        if (object!=null){
                            values.add(object);
                        }
                    }
                    props.put(entry.getKey(), values);
                }
            }else {
                Object object = mapParameterValue(state, context, entry.getKey(), value);
                if (object!=null){
                    props.put(entry.getKey(), object);
                }
            }
        }

        StateInstance stateInstance = new StateInstance(state, props);
        stateInstance.validateProperties();

        return stateInstance;
    }

    private Object mapParameterValue(State state, Map<String, Object> context, String key, StateTemplateNodeParameterValue value){
        if (value.getType() == StateTemplateNodeParameterValue.Type.LITERAL){
            Object literalValue = value.getValue();
            if (literalValue instanceof Double){
                Double doubleValue = (Double)literalValue;
                Property property = state.getProperties().get(key);
                if (property!=null) {
                    if (
                            property.getType() == Property.PropertyType.INT32
                                    || property.getType() == Property.PropertyType.SINT32
                                    || property.getType() == Property.PropertyType.FIXED32
                                    || property.getType() == Property.PropertyType.SFIXED32
                                    || property.getType() == Property.PropertyType.UINT32
                    ) {
                        return doubleValue.intValue();
                    }else if (
                            property.getType() == Property.PropertyType.INT64
                                    || property.getType() == Property.PropertyType.SINT64
                                    || property.getType() == Property.PropertyType.FIXED64
                                    || property.getType() == Property.PropertyType.SFIXED64
                                    || property.getType() == Property.PropertyType.UINT64
                    ) {
                        return doubleValue.longValue();
                    } else if (property.getType() == Property.PropertyType.FLOAT){
                        return doubleValue.floatValue();
                    }else {
                        return doubleValue;
                    }
                }
            }else {
                return literalValue;
            }
        }else if (value.getType() == StateTemplateNodeParameterValue.Type.ID) {
            String idString = value.getValue().toString();
            if (context.get(idString)!=null) {
                Property property = state.getProperties().get(key);
                Object contextValue = context.get(idString);
                if (property.getType().equals(Property.PropertyType.STRING)
                    && (contextValue instanceof EnumerationInstance)){
                    return ((EnumerationInstance)contextValue).getValue();
                }
                return contextValue;
            }
        }else if (value.getType() == StateTemplateNodeParameterValue.Type.REFERENCE) {
            String referenceString = value.getValue().toString();
            Object referenceValue = getObjectValueForReference(context, referenceString);
            Property property = state.getProperties().get(key);
            if (property.getType().equals(Property.PropertyType.STRING)
                    && (referenceValue instanceof EnumerationInstance)){
                return ((EnumerationInstance)referenceValue).getValue();
            }
            return referenceValue;
        }

        return null;
    }

    private Object getObjectValueForReference(Map<String, Object> context, String referenceString){
        String[] parts = referenceString.split("\\.");
        int partsIndex = 0;
        String idString = parts[0];
        Object object = context.get(idString);
        if (object != null){
            partsIndex++;
            while(partsIndex < parts.length){
                if ( (object!=null) && (object instanceof StateInstance) ) {
                    StateInstance si = (StateInstance)object;
                    String part = parts[partsIndex];
                    object = si.getProperties().get(part);
                    partsIndex++;
                } else {
                    return null;
                }
            }
        }
        return object;
    }
}
