package org.stop_lang.templates.models;

import org.stop_lang.models.State;

public class Template {
    private State templateState;
    private TemplateNode root;

    public Template(State templateState, TemplateNode root){
        this.templateState = templateState;
        this.root = root;
    }

    public State getTemplateState(){
        return templateState;
    }

    public TemplateNode getRoot(){
        return root;
    }
}
