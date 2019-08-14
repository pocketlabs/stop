package org.stop_lang.templates.validation;

import org.antlr.symtab.GlobalScope;
import org.antlr.symtab.Scope;
import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.stop_lang.models.Property;
import org.stop_lang.models.State;
import org.stop_lang.models.StateProperty;
import org.stop_lang.models.Stop;
import org.stop_lang.templates.models.*;
import org.stop_lang.templates.parser.StopTemplatesBaseListener;
import org.stop_lang.templates.parser.StopTemplatesParser;
import org.stop_lang.templates.symbols.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RefPhase extends StopTemplatesBaseListener {
    ParseTreeProperty<Scope> scopes;
    GlobalScope globals;
    Scope currentScope; // resolve symbols starting in this scope
    public List<Exception> errors = new ArrayList<Exception>();
    private Stop stop;
    private State templateState;
    private TemplateNode node;
    private TemplateNode lastNode;
    private Property childrenProperty;

    public RefPhase(Stop stop, State templateState, TemplateNode root, GlobalScope globals, ParseTreeProperty<Scope> scopes) {
        this.scopes = scopes;
        this.globals = globals;
        this.stop = stop;
        this.templateState = templateState;
        this.node = root;

        this.childrenProperty = new Property("children", Property.PropertyType.STRING, true, null, false);
        this.globals.define(new ReferenceSymbol(this.childrenProperty.getName(), this.childrenProperty));

        for (Map.Entry<String, Property> propertyEntry : templateState.getProperties().entrySet()){
            this.globals.define(new ReferenceSymbol(propertyEntry.getKey(), propertyEntry.getValue()));
        }
    }

    @Override public void enterFile(StopTemplatesParser.FileContext ctx) {
        currentScope = globals;
    }

    private State getComponent(String componentName){
        State componentState = stop.getStates().get(componentName);
        if (componentState==null){
            for (State state : stop.getStates().values()){
                String name = state.getName();
                if (name.contains(".")){
                    String[] simpleNameParts = name.split("\\.");
                    name = simpleNameParts[simpleNameParts.length-1];
                }
                if (name.equalsIgnoreCase(componentName)){
                    return state;
                }
            }
        }
        return componentState;
    }

    @Override public void enterComponent(StopTemplatesParser.ComponentContext ctx) {
        String componentName = ctx.COMPONENT_TYPE().getText();
        State componentState = getComponent(componentName);
        if (componentState == null){
            errors.add(new StopTemplateValidationException("No component state " + componentName + " found within stop file"));
        }

        StateTemplateNode stateTemplateNode = new StateTemplateNode(node, componentState);
        node.addChild(stateTemplateNode);
        lastNode = stateTemplateNode;

        currentScope = scopes.get(ctx);

        if (currentScope instanceof  ComponentSymbol) {
            ComponentSymbol componentSymbol = (ComponentSymbol)currentScope;
            for (Map.Entry<String, Property> propertyEntry : componentState.getProperties().entrySet()) {
                if (!propertyEntry.getValue().isOptional()) {
                    if(!componentSymbol.getParameters().containsKey(propertyEntry.getKey())){
                        errors.add(new StopTemplateValidationException("Component missing required property " + propertyEntry.getKey()));
                    }
                }
            }

            for(Map.Entry<String, Symbol> parameterEntry: componentSymbol.getParameters().entrySet()){
                Symbol s = parameterEntry.getValue();
                if (s instanceof ParameterSymbol){
                    ParameterSymbol p = (ParameterSymbol)s;
                    if (p.isValue()) {
                        ParameterValueSymbol value = p.getValueSymbol();
                        if (value.isValueID()) {
                            stateTemplateNode.addParameter(p.getName(), new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.ID, value.getValueID()));
                        }else if (value.isValueReference()){
                            stateTemplateNode.addParameter(p.getName(), new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.REFERENCE, value.getValueReference()));
                        } else {
                            stateTemplateNode.addParameter(p.getName(), new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.LITERAL, value.getValue()));
                        }
                    }else if (p.isCollection()){
                        ParameterCollectionSymbol collectionSymbol = p.getCollectionSymbol();
                        StopTemplateNodeParameterCollection params = new StopTemplateNodeParameterCollection(StateTemplateNodeParameterValue.Type.COLLECTION);
                        for (ParameterValueSymbol value : collectionSymbol.getValueSymbols()){
                            if (value.isValueID()) {
                                params.addParameterValue(new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.ID, value.getValueID()));
                            } else if (value.isValueReference()){
                                params.addParameterValue(new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.REFERENCE, value.getValueReference()));
                            } else {
                                params.addParameterValue(new StateTemplateNodeParameterValue(StateTemplateNodeParameterValue.Type.LITERAL, value.getValue()));
                            }
                        }
                        stateTemplateNode.addParameter(p.getName(), params);
                    }
                }
            }
        }
    }

    @Override public void exitComponent(StopTemplatesParser.ComponentContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterCollection(StopTemplatesParser.CollectionContext ctx) {
        String id;
        Property property;
        boolean isOptional = false;
        List<Property> properties = null;

        if (ctx.REFERENCE()!=null){
            id = ctx.REFERENCE().getText();
            properties = findReferenceProperty(id);
            if (properties == null){
                errors.add(new StopTemplateValidationException("Couldn't find reference " + id));
                return;
            }
            property = properties.get(properties.size()-1);
            for (Property p : properties){
                if (p.isOptional()){
                    isOptional = true;
                    break;
                }
            }
        }else {
            id = ctx.ID().getText();
            property = findParentProperty(currentScope, id);
            isOptional = property.isOptional();
        }

        if (property == null){
            errors.add(new StopTemplateValidationException("No property " + id + " found within state " + templateState.getName()));
            return;
        }
        if (!property.isCollection()){
            errors.add(new StopTemplateValidationException("No collection " + id + " found within state " + templateState.getName()));
            return;
        }

        currentScope = scopes.get(ctx);

        if (ctx.NOT() == null) {
            Property collectionItem = new Property(property.getName(), property.getType(), false, null, false);

            if (property instanceof StateProperty) {
                StateProperty stateProperty = (StateProperty) property;
                collectionItem = new StateProperty(property.getName()+"_item", stateProperty.getState(), false, null, false, null);
                for (Map.Entry<String, Property> statePropertyEntry : stateProperty.getState().getProperties().entrySet()) {
                    currentScope.getEnclosingScope().define(new ReferenceSymbol(statePropertyEntry.getKey(), statePropertyEntry.getValue()));
                }
            }

            currentScope.getEnclosingScope().define(new ReferenceSymbol(property.getName()+"_item", collectionItem));
        }


        CollectionTemplateNode collectionTemplateNode;
        if (properties!=null){
            collectionTemplateNode = new CollectionTemplateNode(node, properties, ctx.NOT() != null);
        }else {
            collectionTemplateNode = new CollectionTemplateNode(node, property, ctx.NOT() != null);
        }

        node.addChild(collectionTemplateNode);
        lastNode = collectionTemplateNode;
    }

    @Override public void exitCollection(StopTemplatesParser.CollectionContext ctx) {
        currentScope = currentScope.getEnclosingScope();
    }

    @Override public void enterConditional(StopTemplatesParser.ConditionalContext ctx) {
        String id;
        Property property;
        boolean isOptional = false;
        List<Property> properties = null;

        if (ctx.REFERENCE()!=null){
            id = ctx.REFERENCE().getText();
            properties = findReferenceProperty(id);
            if (properties == null){
                errors.add(new StopTemplateValidationException("Couldn't find reference " + id));
                return;
            }
            property = properties.get(properties.size()-1);
            for (Property p : properties){
                if (p.isOptional()){
                    isOptional = true;
                    break;
                }
            }
        }else {
            id = ctx.ID().getText();
            property = findParentProperty(currentScope, id);
            isOptional = property.isOptional();
        }

        if (property == null) {
            errors.add(new StopTemplateValidationException("No property " + id + " found within state " + templateState.getName()));
            return;
        }
        if (!(isOptional || property.isCollection() || (property.getType().equals(Property.PropertyType.BOOL)))) {
            errors.add(new StopTemplateValidationException("Property " + id + " found within state " + templateState.getName() + " but it is not optional, collection or boolean"));
            return;
        }

        currentScope = scopes.get(ctx);

        ConditionalTemplateNode conditionalTemplateNode;
        if (properties!=null){
            conditionalTemplateNode = new ConditionalTemplateNode(node, properties, ctx.NOT() != null);
        }else{
            conditionalTemplateNode = new ConditionalTemplateNode(node, property, ctx.NOT() != null);
        }

        node.addChild(conditionalTemplateNode);
        lastNode = conditionalTemplateNode;

        if (ctx.NOT() == null) {
            if (property instanceof StateProperty) {
                StateProperty stateProperty = (StateProperty) property;
                for (Map.Entry<String, Property> statePropertyEntry : stateProperty.getState().getProperties().entrySet()) {
                    currentScope.getEnclosingScope().define(new ReferenceSymbol(statePropertyEntry.getKey(), statePropertyEntry.getValue()));
                }
            }
        }
    }

    @Override public void enterComponent_parameter(StopTemplatesParser.Component_parameterContext ctx) {
        String id = ctx.ID().getText();

        if( currentScope instanceof ComponentSymbol) {
            ComponentSymbol componentSymbol = (ComponentSymbol) currentScope;
            String componentName = componentSymbol.getName();
            State componentState = getComponent(componentName);
            if (componentState == null){
                errors.add(new StopTemplateValidationException("No component state " + componentName + " found within stop file"));
            }else{
                Property property = componentState.getProperties().get(id);
                if (property == null){
                    errors.add(new StopTemplateValidationException("No property " + id + " found within state " + componentState.getName()));
                } else {
                    Symbol symbol = componentSymbol.getParameters().get(id);
                    if ((symbol!=null) && (symbol instanceof  ParameterSymbol)){
                        ParameterSymbol parameterSymbol = (ParameterSymbol)symbol;
                        if (parameterSymbol.isValue()) {
                            ParameterValueSymbol parameterValueSymbol = parameterSymbol.getValueSymbol();
                            validateParameterValueSymbol(id, property, parameterValueSymbol);
                        }else if (parameterSymbol.isCollection()){
                            for (ParameterValueSymbol parameterValueSymbol : parameterSymbol.getCollectionSymbol().getValueSymbols()){
                                validateParameterValueSymbol(id, property, parameterValueSymbol);
                            }
                        }
                    }else {
                        errors.add(new StopTemplateValidationException("No property found " + id + " found within state " + componentState.getName()));
                    }
                }
            }
        }else {
            errors.add(new StopTemplateValidationException("No component found"));
        }
    }

    private void validateParameterValueSymbol(String id, Property property, ParameterValueSymbol parameterValueSymbol){
        if (parameterValueSymbol.isValueID()) {
            String valueName = parameterValueSymbol.getValue().toString();
            Property foundProperty = findParentProperty(currentScope.getEnclosingScope(), valueName);
            if (foundProperty == null) {
                errors.add(new StopTemplateValidationException("Property " + valueName + " could not be found"));
                return;
            } else {
                if (!doesPropertyMapToProperty(property, foundProperty)){
                    errors.add(new StopTemplateValidationException("Property " + valueName + " doesn't match type"));
                    return;
                }
                if (property instanceof StateProperty){
                    if (foundProperty instanceof StateProperty){
                        StateProperty stateProperty = (StateProperty)property;
                        StateProperty foundStateProperty = (StateProperty)foundProperty;

                        if (!stateProperty.getState().getName().equalsIgnoreCase(foundStateProperty.getState().getName())){
                            errors.add(new StopTemplateValidationException("Property " + valueName + " is not a "+ stateProperty.getState().getName() +" state property"));
                            return;
                        }
                    }else{
                        errors.add(new StopTemplateValidationException("Property " + valueName + " is not a state property"));
                        return;
                    }
                }
                if (foundProperty.isOptional() && !property.isOptional()) {
                    errors.add(new StopTemplateValidationException("Property " + valueName + " has optional(" + foundProperty.isOptional() + ") while component property " + id + " has optional(" + property.isOptional() + ")"));
                    return;
                }
            }
        } else if (parameterValueSymbol.isValueReference()) {
            String reference = parameterValueSymbol.getValue().toString();
            validateReference(id, property, reference);
        } else if (parameterValueSymbol.isValueNumber()) {
            if (!property.getType().equals(Property.PropertyType.INT32)
                    && !property.getType().equals(Property.PropertyType.INT32)
                    && !property.getType().equals(Property.PropertyType.INT64)
                    && !property.getType().equals(Property.PropertyType.SINT32)
                    && !property.getType().equals(Property.PropertyType.SINT64)
                    && !property.getType().equals(Property.PropertyType.FIXED32)
                    && !property.getType().equals(Property.PropertyType.FIXED64)
                    && !property.getType().equals(Property.PropertyType.UINT32)
                    && !property.getType().equals(Property.PropertyType.UINT64)
                    && !property.getType().equals(Property.PropertyType.DOUBLE)
                    && !property.getType().equals(Property.PropertyType.FLOAT)
            ) {
                errors.add(new StopTemplateValidationException("Property " + id + " should be a number"));
                return;
            }
        } else if (parameterValueSymbol.isValueBoolean()) {
            if (!property.getType().equals(Property.PropertyType.BOOL)) {
                errors.add(new StopTemplateValidationException("Property " + id + " should be a bool"));
                return;
            }
        } else if (parameterValueSymbol.isValueString()) {
            if (!property.getType().equals(Property.PropertyType.STRING)) {
                errors.add(new StopTemplateValidationException("Property " + id + " should be a string"));
                return;
            }
        }
    }

    @Override public void enterNested_statements(StopTemplatesParser.Nested_statementsContext ctx) {
        node = lastNode;
    }

    @Override public void exitNested_statements(StopTemplatesParser.Nested_statementsContext ctx) {
        node = node.getParent();
    }

    private Property findParentProperty(Scope scope, String name){
        Symbol symbol = scope.getSymbol(name);
        if ( symbol!=null){
            if (symbol instanceof  ReferenceSymbol){
                return ((ReferenceSymbol)symbol).getProperty();
            }
        }

        for (Scope nestedScope : scope.getNestedScopes()){
            Symbol nestedSymbol = nestedScope.getSymbol(name);
            if ( nestedSymbol!=null){
                if (nestedSymbol instanceof  ReferenceSymbol){
                    return ((ReferenceSymbol)nestedSymbol).getProperty();
                }
            }
        }

        Scope parentScope = scope.getEnclosingScope();
        if (parentScope!=null){
            return findParentProperty(parentScope, name);
        }

        return null;
    }

    private List<Property> findReferenceProperty(String reference){
        String[] parts = reference.split("\\.");
        int partIndex = 0;
        String valueName = parts[0];
        Property subProperty = null;
        List<Property> properties = new ArrayList<>();

        Property foundProperty = findParentProperty(currentScope.getEnclosingScope(), valueName);
        if (foundProperty!=null){
            partIndex = 1;
            properties.add(foundProperty);
            while (partIndex < parts.length) {
                if ((foundProperty!=null) && (foundProperty instanceof StateProperty)) {
                    StateProperty stateProperty = (StateProperty) foundProperty;
                    String part = parts[partIndex];
                    subProperty = stateProperty.getState().getProperties().get(part);
                    if (subProperty != null) {
                        partIndex++;
                        foundProperty = subProperty;
                        properties.add(subProperty);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }

        return properties;
    }

    private void validateReference(String id, Property property, String reference){
        String[] parts = reference.split("\\.");
        int partIndex = 0;
        String valueName = parts[0];

        Property foundProperty = findParentProperty(currentScope.getEnclosingScope(), valueName);
        if (foundProperty == null) {
            errors.add(new StopTemplateValidationException("Property " + valueName + " could not be found"));
            return;
        } else {
            if (foundProperty.isOptional() && !property.isOptional()) {
                errors.add(new StopTemplateValidationException("Property " + valueName + " has optional(" + foundProperty.isOptional() + ") while component property " + id + " has optional(" + property.isOptional() + ")"));
                return;
            }
            partIndex = 1;
            if (foundProperty instanceof StateProperty){
                StateProperty stateProperty = (StateProperty)foundProperty;
                Property subProperty = null;
                while (partIndex < parts.length){
                    String part = parts[partIndex];
                    subProperty = stateProperty.getState().getProperties().get(part);
                    if ( subProperty != null){
                        if (subProperty.isOptional() && !property.isOptional()) {
                            errors.add(new StopTemplateValidationException("Property " + valueName + " has optional(" + subProperty.isOptional() + ") while component property " + id + " has optional(" + property.isOptional() + ")"));
                            return;
                        }
                        if (partIndex < (parts.length - 1)) {
                            if (subProperty instanceof StateProperty){
                                stateProperty = (StateProperty)subProperty;
                            }else {
                                errors.add(new StopTemplateValidationException("Can't continue because property " + subProperty.getName() + " is not a state property"));
                                return;
                            }
                        }
                        partIndex++;
                    }else {
                        errors.add(new StopTemplateValidationException("Property " + valueName + " is not a state property and sub property " + part + " could not be found"));
                        return;
                    }
                }
                if (subProperty!= null){
                    if (!doesPropertyMapToProperty(property, subProperty)){
                        errors.add(new StopTemplateValidationException("Property " + subProperty.getName() + " doesn't match type"));
                        return;
                    }
                    if (property instanceof StateProperty){
                        if (subProperty instanceof StateProperty){
                            StateProperty s1 = (StateProperty)property;
                            StateProperty s2 = (StateProperty)subProperty;

                            if (!s1.getState().getName().equalsIgnoreCase(s2.getState().getName())){
                                errors.add(new StopTemplateValidationException("Property " + subProperty.getName() + " is not a "+ s1.getState().getName() +" state property"));
                                return;
                            }
                        }else{
                            errors.add(new StopTemplateValidationException("Property " + subProperty.getName() + " is not a state property"));
                            return;
                        }
                    }
                    if (subProperty.isOptional() && !property.isOptional()) {
                        errors.add(new StopTemplateValidationException("Property " + valueName + " has optional(" + subProperty.isOptional() + ") while component property " + id + " has optional(" + property.isOptional() + ")"));
                        return;
                    }
                }
            }else{
                errors.add(new StopTemplateValidationException("Property " + valueName + " is not a state property"));
                return;
            }
        }
    }

    private boolean doesPropertyMapToProperty(Property a, Property b){
        if (b.getType().equals(Property.PropertyType.ENUM)
            && a.getType().equals(Property.PropertyType.STRING)){
            return true;
        }
        return a.getType().equals(b.getType());
    }
}
