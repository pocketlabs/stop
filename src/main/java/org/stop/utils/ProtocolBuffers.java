package org.stop.utils;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.stop.models.*;
import org.stop.validation.Validator;

import java.io.IOException;

public class ProtocolBuffers {
    public ProtocolBuffers(){}

    public String generate(CharStream input) throws IOException {
        App app = Validator.getApp(input);

        if (app == null){
            throw new IOException("Invalid stop file");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("syntax = \"proto3\";\n\n");

        for (State state : app.getStates().values()){
            sb.append("message " + state.getName() + " {\n");
            int index = 1;
            for (Enumeration enumeration : state.getEnumerations().values()){
                int enumIndex = 0;
                sb.append("\tenum " + enumeration.getName() + " {\n");
                for (String value : enumeration.getValues()){
                    sb.append("\t\t" + value + " = " + enumIndex + ";\n");
                    enumIndex++;
                }
                sb.append("\t}\n");
            }
            for ( Property property : state.getProperties().values()){
                String repeated = "";
                if (property.isCollection()){
                    repeated = "repeated ";
                }
                String type;
                if(property instanceof StateProperty) {
                    StateProperty stateProperty = (StateProperty) property;
                    type = stateProperty.getState().getName();
                }else if (property instanceof EnumerationProperty){
                    EnumerationProperty enumProperty = (EnumerationProperty) property;
                    type = enumProperty.getEnumeration().getName();
                }else {
                    type = getProtocolBuffersTypeForPropertyType(property.getType());
                }
                sb.append("\t" + repeated + type + " " + property.getName() + " = " + index + ";\n");
                index++;
            }
            sb.append("}\n");
        }

        return sb.toString();
    }

    private String getProtocolBuffersTypeForPropertyType(Property.PropertyType propertyType){
        switch(propertyType){
            case DOUBLE:
                return "double";
            case FLOAT:
                return "float";
            case INT32:
                return "int32";
            case INT64:
                return "int64";
            case UINT32:
                return "uint32";
            case UINT64:
                return "uint64";
            case SINT32:
                return "sint32";
            case SINT64:
                return "sint64";
            case FIXED32:
                return "fixed32";
            case FIXED64:
                return "fixed64";
            case SFIXED32:
                return "sfixed32";
            case SFIXED64:
                return "sfixed64";
            case BOOL:
                return "bool";
            case STRING:
                return "string";
            case BYTES:
                return "bytes";
            default:
                throw new IllegalArgumentException("Invalid type supplied " + propertyType);
        }
    }

    public static void main(String[] args) {
        try {
            String filename = args[0];
            CharStream input = CharStreams.fromFileName(filename);
            ProtocolBuffers protocolBuffers = new ProtocolBuffers();
            String generated = protocolBuffers.generate(input);
            System.out.println(generated);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
