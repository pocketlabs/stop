package org.stop_lang.runtime.tests;

import org.junit.jupiter.api.Test;
import org.stop_lang.models.*;
import org.stop_lang.runtime.StopRuntime;
import org.stop_lang.validation.Validator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuntimeTest {

    @Test
    public void transition() throws Exception {
        Stop stop = Validator.getStop("./examples/kitchen-sink.stop");
        assert(stop != null);

        StopRuntime runtime = new StopRuntime(stop);

        State renderState = stop.getStates().get("Render");
        Map<String, Object> renderProperties = new HashMap<String, Object>();
        renderProperties.put("html", "<h1>test</h1>");
        StateInstance renderInstance = renderState.buildInstance(renderProperties);

        State responseState = stop.getStates().get("Response");
        Map<String, Object> responseProperties = new HashMap<String, Object>();
        responseProperties.put("content", "<h1>test</h1>");
        responseProperties.put("content_type", "text/html");
        responseProperties.put("status", 200);
        responseProperties.put("headers", new ArrayList());
        StateInstance responseInstance = responseState.buildInstance(responseProperties);

        assertEquals(runtime.transition(renderInstance, responseInstance), true);
    }

    @Test
    public void transitionWithDynamicProperties() throws Exception {
        Stop stop = Validator.getStop("./examples/kitchen-sink.stop");
        assert(stop != null);

        StopRuntime runtime = new StopRuntime(stop);

        State userState = stop.getStates().get("User");
        Map<String, Object> userProperties = new HashMap<String, Object>();
        userProperties.put("id", 1L);
        userProperties.put("email", "email");
        userProperties.put("username", "username");
        StateInstance userInstance = userState.buildInstance(userProperties);

        State authState = stop.getStates().get("Authenticate");
        Map<String, Object> authProperties = new HashMap<String, Object>();
        authProperties.put("login", "login");
        authProperties.put("password", "password");
        authProperties.put("user", userInstance);
        StateInstance authInstance = authState.buildInstance(authProperties);

        State redirectState = stop.getStates().get("Redirect");
        Map<String, Object> redirectProperties = new HashMap<String, Object>();
        redirectProperties.put("url", "http://test.com");
        StateInstance redirectInstance = redirectState.buildInstance(redirectProperties);

        assertEquals(runtime.transition(authInstance, redirectInstance), true);

        State headerState = stop.getStates().get("Header");
        Map<String, Object> headerProperties = new HashMap<String, Object>();
        headerProperties.put("name", "Location");
        headerProperties.put("value", "http://test.com");
        StateInstance headerInstance = headerState.buildInstance(headerProperties);
        List headers = new ArrayList();
        headers.add(headerInstance);

        State responseState = stop.getStates().get("Response");
        Map<String, Object> responseProperties = new HashMap<String, Object>();
        responseProperties.put("content", "");
        responseProperties.put("content_type", "");
        responseProperties.put("status", 302);
        responseProperties.put("headers", headers);
        StateInstance responseInstance = responseState.buildInstance(responseProperties);

        assertEquals(runtime.transition(redirectInstance, responseInstance), true);
    }

    @Test
    public void transitionWithOptionals() throws Exception {
        Stop stop = Validator.getStop("./examples/kitchen-sink.stop");
        assert(stop != null);

        StopRuntime runtime = new StopRuntime(stop);

        State renderState = stop.getStates().get("Request");
        Enumeration methodEnumeration = renderState.getEnumerations().get("Method");
        EnumerationInstance methodInstance = new EnumerationInstance(methodEnumeration, "GET");
        Map<String, Object> requestProperties = new HashMap<String, Object>();
        requestProperties.put("headers", new ArrayList());
        requestProperties.put("parameters", new ArrayList());
        requestProperties.put("method", methodInstance);
        requestProperties.put("path", "/test");
        requestProperties.put("time", Instant.now());
        StateInstance requestInstance = renderState.buildInstance(requestProperties);

        State routerState = stop.getStates().get("Router");
        Map<String, Object> routerProperties = new HashMap<String, Object>();
        routerProperties.put("request", requestInstance);
        StateInstance routerInstance = routerState.buildInstance(routerProperties);

        State loginState = stop.getStates().get("Login");
        StateInstance loginInstance = loginState.buildInstance(new HashMap());

        assertEquals(runtime.transition(routerInstance, loginInstance), true);
    }
}
