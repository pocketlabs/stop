package org.stop_lang.validation;

import org.antlr.symtab.Symbol;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.stop_lang.models.*;
import org.stop_lang.parser.StopLexer;
import org.stop_lang.parser.StopParser;
import org.stop_lang.symbols.*;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class Validator {
    public static boolean validate(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return validate(input);
    }

    public static boolean validate(CharStream input) {
        Stop stop = getStop(input);
        return stop != null;
    }

    public static Stop getStop(String filename) throws IOException {
        CharStream input = CharStreams.fromFileName(filename);
        return getStop(input);
    }

    public static Stop getStop(CharStream input) {
        StopLexer l = new StopLexer(input);
        TokenStream tokens = new CommonTokenStream(l);
        StopParser parser = new StopParser(tokens);
        ParserRuleContext tree = parser.file();

        if (tree.exception != null){
            return null;
        }

        ParseTreeWalker walker = new ParseTreeWalker();

        IncludePhase includes = new IncludePhase();
        walker.walk(includes, tree);

        if (handleErrors(includes.errors) ){
            return null;
        }

        DefPhase def = new DefPhase();
        walker.walk(def, tree);

        if (handleErrors(def.errors) ){
            return null;
        }

        RefPhase ref = new RefPhase(def.globals, def.scopes);
        walker.walk(ref, tree);

        if (handleErrors(ref.errors) ){
            return null;
        }

        TransitionPhase transition = new TransitionPhase(def.globals, def.scopes);
        walker.walk(transition, tree);

        if (handleErrors(transition.errors) ){
            return null;
        }

        StopPhase stop = new StopPhase(def.globals, def.scopes);
        walker.walk(stop, tree);

        if (handleErrors(stop.errors) ){
            return null;
        }

        TreeMap<String, State> states = new TreeMap<String, State>(String.CASE_INSENSITIVE_ORDER);

        for (Symbol symbol : def.globals.getSymbols()){
            if (symbol instanceof ModelSymbol){
                ModelSymbol modelSymbol = (ModelSymbol)symbol;
                String name = modelSymbol.getName();
                if (modelSymbol.getAsync()){
                    AsyncState asyncState = new AsyncState(name, modelSymbol.timeout);
                    states.put(name, asyncState);
                }else{
                    State.StateType type = State.StateType.SYNC;

                    if (modelSymbol.getStop()){
                        type = State.StateType.STOP;
                    } else if (modelSymbol.getStart()){
                        type = State.StateType.START;
                    } else if (modelSymbol.getQueue()){
                        type = State.StateType.QUEUE;
                    }

                    State state = new State(name, type);
                    states.put(name, state);
                }
            }
        }

        for (Symbol symbol : def.globals.getSymbols()){
            if (symbol instanceof ModelSymbol){
                ModelSymbol modelSymbol = (ModelSymbol)symbol;
                String name = modelSymbol.getName();
                State modelState = states.get(name);

                if (modelState == null){
                    return null;
                }

                if (modelState instanceof AsyncState){
                    AsyncState asyncState = (AsyncState)modelState;

                    String transitionName = modelSymbol.getTimeoutTransition();
                    State timeoutState = states.get(transitionName);
                    if (timeoutState != null){
                        asyncState.setTimeoutTransition(timeoutState);
                    }
                }

                TreeMap<String, State> transitions = new TreeMap<String, State>(String.CASE_INSENSITIVE_ORDER);
                for(String transitionName : modelSymbol.getTransitions()){
                    State transitionState = states.get(transitionName);
                    if (transitionState != null){
                        transitions.put(transitionName, transitionState);
                    }
                }
                modelState.setTransitions(transitions);

                TreeMap<String, State> enqueues = new TreeMap<String, State>(String.CASE_INSENSITIVE_ORDER);
                for(String enqueueName : modelSymbol.getEnqueues()){
                    State enqueueState = states.get(enqueueName);
                    if (enqueueState != null){
                        transitions.put(enqueueName, enqueueState);
                    }
                }
                modelState.setEnqueues(enqueues);

                TreeMap<String, State> errors = new TreeMap<String, State>(String.CASE_INSENSITIVE_ORDER);
                for(String errorTransitionName : modelSymbol.getErrorTypes()) {
                    State errorState = states.get(errorTransitionName);
                    if (errorState!=null){
                        errors.put(errorTransitionName, errorState);
                    }
                }
                modelState.setErrors(errors);

                TreeMap<String, Enumeration> enumerations = new TreeMap<String, Enumeration>(String.CASE_INSENSITIVE_ORDER);
                for (Symbol childSymbol : modelSymbol.getSymbols()){
                    if (childSymbol instanceof EnumSymbol){
                        EnumSymbol enumSymbol = (EnumSymbol)childSymbol;
                        Enumeration enumeration = new Enumeration(enumSymbol.getName(), enumSymbol.getValues());
                        enumerations.put(enumeration.getName(), enumeration);
                    }
                }
                modelState.setEnumerations(enumerations);

                TreeMap<String, Property> properties = new TreeMap<String, Property>(String.CASE_INSENSITIVE_ORDER);
                for (Symbol childSymbol : modelSymbol.getSymbols()){
                    if (childSymbol instanceof StopFieldSymbol) {
                        StopFieldSymbol stopFieldSymbol = (StopFieldSymbol)childSymbol;
                        String symbolName = childSymbol.getName();
                        Property property = null;
                        State provider = null;
                        boolean optional = stopFieldSymbol.isOptional();
                        if ( stopFieldSymbol.getAsyncSource() != null){
                            provider = states.get(stopFieldSymbol.getAsyncSource());
                        }
                        if(childSymbol instanceof ModelFieldSymbol) {
                            State fieldState = states.get(stopFieldSymbol.getTypeName());
                            if (fieldState != null) {
                                property = new StateProperty(symbolName, fieldState, false, provider, optional);
                            }
                            Enumeration enumeration = enumerations.get(stopFieldSymbol.getTypeName());
                            if(enumeration!=null){
                                property = new EnumerationProperty(symbolName, enumeration, false, provider, optional);
                            }
                        }else if (childSymbol instanceof CollectionFieldSymbol){
                            CollectionFieldSymbol collectionFieldSymbol = (CollectionFieldSymbol)childSymbol;
                            Property.PropertyType propertyType;
                            if (collectionFieldSymbol.isState()){
                                String typeName = collectionFieldSymbol.getTypeName();
                                State fieldState = states.get(typeName);
                                property = new StateProperty(symbolName, fieldState, true, provider, optional);
                            }else {
                                Enumeration enumeration = enumerations.get(collectionFieldSymbol.getTypeName());
                                if(enumeration!=null){
                                    property = new EnumerationProperty(symbolName, enumeration, false, provider, optional);
                                }else {
                                    propertyType = getPropertyType(collectionFieldSymbol.getTypeName());
                                    property = new Property(symbolName, propertyType, true, provider, optional);
                                }
                            }
                        }else if (childSymbol instanceof ScalarFieldSymbol){
                            ScalarFieldSymbol scalarFieldSymbol = (ScalarFieldSymbol)childSymbol;
                            Property.PropertyType propertyType = getPropertyType(scalarFieldSymbol.getTypeName());

                            if(propertyType!=null){
                                property = new Property(symbolName, propertyType, false, provider, optional);
                            }
                        }
                        if (property!=null){
                            properties.put(symbolName, property);
                        }
                    }
                }
                modelState.setProperties(properties);
            }
        }

        Stop app = new Stop(states);

        return app;
    }

    private static boolean handleErrors(List<Exception> exceptions){
        if (!exceptions.isEmpty()) {
            for (Exception exception : exceptions){
                System.err.println(exception.getMessage());
            }
            return true;
        }
        return false;
    }

    private static Property.PropertyType getPropertyType(String typeName){
        switch(typeName){
            case "double":
                return Property.PropertyType.DOUBLE;
            case "float":
                return Property.PropertyType.FLOAT;
            case "int32":
                return Property.PropertyType.INT32;
            case "int64":
                return Property.PropertyType.INT64;
            case "uint32":
                return Property.PropertyType.UINT32;
            case "uint64":
                return Property.PropertyType.UINT64;
            case "sint32":
                return Property.PropertyType.SINT32;
            case "sint64":
                return Property.PropertyType.SINT64;
            case "fixed32":
                return Property.PropertyType.FIXED32;
            case "fixed64":
                return Property.PropertyType.FIXED64;
            case "sfixed32":
                return Property.PropertyType.SFIXED32;
            case "sfixed64":
                return Property.PropertyType.SFIXED64;
            case "bool":
                return Property.PropertyType.BOOL;
            case "string":
                return Property.PropertyType.STRING;
            case "bytes":
                return Property.PropertyType.BYTES;
            default:
                throw new IllegalArgumentException("Invalid type name");
        }
    }
}
