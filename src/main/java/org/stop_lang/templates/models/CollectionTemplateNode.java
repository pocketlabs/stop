package org.stop_lang.templates.models;

import org.stop_lang.models.Property;

import java.util.List;

public class CollectionTemplateNode extends TemplateNode {
    private Property property;
    private boolean empty;
    private List<Property> properties;

    public CollectionTemplateNode(Property property, boolean empty){
        super();
        this.property = property;
        this.empty = empty;
    }

    public CollectionTemplateNode(TemplateNode parent, Property property, boolean empty){
        super(parent);
        this.property = property;
        this.empty = empty;
    }

    public CollectionTemplateNode(List<Property> properties, boolean empty){
        super();
        this.properties = properties;
        this.empty = empty;
    }

    public CollectionTemplateNode(TemplateNode parent, List<Property> properties, boolean empty){
        super(parent);
        this.properties = properties;
        this.empty = empty;
    }

    public boolean getEmpty(){
        return empty;
    }

    public Property getProperty(){
        if ((this.properties!=null) && !this.properties.isEmpty()){
            return this.properties.get(this.properties.size()-1);
        }
        return property;
    }

    public List<Property> getProperties(){ return properties; }
}
