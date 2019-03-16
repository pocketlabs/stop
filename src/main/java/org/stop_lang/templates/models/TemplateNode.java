package org.stop_lang.templates.models;

import java.util.ArrayList;
import java.util.Collection;

abstract public class TemplateNode {
    private Collection<TemplateNode> children;
    private TemplateNode parent;

    public TemplateNode(){
        this.children = new ArrayList<>();
    }

    public TemplateNode(TemplateNode parent){
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void addChild(TemplateNode child){
        children.add(child);
    }

    public Collection<TemplateNode> getChildren(){
        return children;
    }

    public TemplateNode getParent(){
        return parent;
    }
}
