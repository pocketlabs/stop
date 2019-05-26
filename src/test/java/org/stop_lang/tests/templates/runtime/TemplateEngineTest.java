package org.stop_lang.tests.templates.runtime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.stop_lang.models.State;
import org.stop_lang.models.StateInstance;
import org.stop_lang.models.Stop;
import org.stop_lang.templates.models.Template;
import org.stop_lang.templates.runtime.TemplateEngine;
import org.stop_lang.templates.runtime.TemplateEngineException;
import org.stop_lang.templates.runtime.TemplateEngineImplementation;
import org.stop_lang.templates.validation.Validator;

import java.util.*;

public class TemplateEngineTest {

    @Test
    public void test() throws Exception{
        Stop stop = new Stop("./examples/templates/templates.stop");
        State templateStop = stop.getStates().get("Posts");
        Template template = Validator.getTemplate(stop, templateStop, "./examples/templates/posts.st");

        Assert.assertNotNull(template);

        Map<String, Object> props = new HashMap<>();
        props.put("fuck", "test");
        props.put("boolTest", true);

        Collection a = new ArrayList();
        Map<String, Object> post = new HashMap<>();
        post.put("title", "test post");
        post.put("body", "test body");
        post.put("description", "wow");
        List tags = new ArrayList();
        tags.add("fuck");
        tags.add("the");
        tags.add("noise");
        post.put("tags", tags);
        Map<String, Object> userProps = new HashMap<>();
        userProps.put("name", "Kyle");
        List<String> aliases = new ArrayList<>();
        aliases.add("fuckdong");
//        userProps.put("aliases", aliases);
        State userState = stop.getStates().get("User");
        StateInstance user = new StateInstance(userState, userProps);
        post.put("user", user);
        props.put("realUser", user);
        State postState = stop.getStates().get("Post");
        StateInstance postInstance = new StateInstance(postState, post);
        a.add(postInstance);
        props.put("posts", a);

        TemplateEngineImplementation<String> i = new TemplateEngineImplementation<String>() {
            @Override
            public String executeTemplate(StateInstance stateInstance, Collection<String> children) throws TemplateEngineException {
                StringBuilder sb = new StringBuilder();
                sb.append("<" + stateInstance.getState().getName()+" " + stateInstance.getProperties().toString()+">\n");
                for(String child : children){
                    sb.append(child + "\n");
                }
                sb.append("</" + stateInstance.getState().getName()+">\n");
                return sb.toString();
            }

            @Override
            public String renderTemplateChildren(Collection<String> children) {
                StringBuilder sb = new StringBuilder();
                for (String childString : children) {
                    sb.append(childString);
                }

                return sb.toString();
            }
        };

        StateInstance templateStateInstance = new StateInstance(templateStop, props);
        TemplateEngine<String> templateEngine = new TemplateEngine<String>(i, template);
        String output = templateEngine.render(templateStateInstance);

        System.out.println(output);
    }
}
