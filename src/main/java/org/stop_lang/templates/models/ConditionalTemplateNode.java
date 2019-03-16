package org.stop_lang.templates.models;

import org.stop_lang.models.Property;

import java.util.List;

public class ConditionalTemplateNode  extends TemplateNode {
    private Property property;
    private boolean empty;
    private List<Property> properties;

    public ConditionalTemplateNode(Property property, boolean empty){
        super();
        this.property = property;
        this.empty = empty;
    }

    public ConditionalTemplateNode(TemplateNode parent, Property property, boolean empty){
        super(parent);
        this.property = property;
        this.empty = empty;
    }

    public ConditionalTemplateNode(List<Property> properties, boolean empty){
        super();
        this.properties = properties;
        this.empty = empty;
    }

    public ConditionalTemplateNode(TemplateNode parent, List<Property> properties, boolean empty){
        super(parent);
        this.properties = properties;
        this.empty = empty;
    }

    public boolean getEmpty(){
        return empty;
    }

    public Property getProperty(){
        return property;
    }

    public List<Property> getProperties(){ return properties; }
}
