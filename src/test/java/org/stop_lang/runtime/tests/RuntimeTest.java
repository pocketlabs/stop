package org.stop_lang.runtime.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.stop_lang.models.*;
import org.stop_lang.runtime.StopRuntime;
import org.stop_lang.runtime.StopRuntimeException;
import org.stop_lang.runtime.tests.helloworld.HelloRuntime;
import org.stop_lang.runtime.tests.helloworld.HelloRuntimeBase;
import org.stop_lang.validation.Validator;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuntimeTest {

    @Test
    public void smoke() throws Exception {
        HelloRuntime runtime = new HelloRuntime();
        HelloRuntimeBase startInstance = new HelloRuntimeBase("A");
        startInstance.put("test1", "hey now");
        HelloRuntimeBase stop = runtime.getRuntime().start(startInstance);
        assert(stop!=null);
//        assertEquals(stop.getName(), "C");
    }

//    @Test
//    public void transition() throws Exception {
//        Stop stop = new Stop("./examples/kitchen-sink.stop");
//        assert(stop != null);
//
//        StopRuntime runtime = new StopRuntime(stop);
//
//        State renderState = stop.getStates().get("Render");
//        Map<String, Object> renderProperties = new HashMap<String, Object>();
//        renderProperties.put("html", "<h1>test</h1>");
//        StateInstance renderInstance = renderState.buildInstance(renderProperties);
//
//        State responseState = stop.getStates().get("Response");
//        Map<String, Object> responseProperties = new HashMap<String, Object>();
//        responseProperties.put("content", "<h1>test</h1>");
//        responseProperties.put("content_type", "text/html");
//        responseProperties.put("status", 200);
//        responseProperties.put("headers", new ArrayList());
//        StateInstance responseInstance = responseState.buildInstance(responseProperties);
//
//        runtime.transition(renderInstance, responseInstance);
//    }
//
//    @Test
//    public void transitionWithDynamicProperties() throws Exception {
//        Stop stop = new Stop("./examples/kitchen-sink.stop");
//        assert(stop != null);
//
//        StopRuntime runtime = new StopRuntime(stop);
//
//        State userState = stop.getStates().get("User");
//        Map<String, Object> userProperties = new HashMap<String, Object>();
//        userProperties.put("id", 1L);
//        userProperties.put("email", "email");
//        userProperties.put("username", "username");
//        StateInstance userInstance = userState.buildInstance(userProperties);
//
//        State authState = stop.getStates().get("Authenticate");
//        Map<String, Object> authProperties = new HashMap<String, Object>();
//        authProperties.put("login", "login");
//        authProperties.put("password", "password");
//        authProperties.put("user", userInstance);
//        StateInstance authInstance = authState.buildInstance(authProperties);
//
//        State redirectState = stop.getStates().get("Redirect");
//        Map<String, Object> redirectProperties = new HashMap<String, Object>();
//        redirectProperties.put("url", "http://test.com");
//        StateInstance redirectInstance = redirectState.buildInstance(redirectProperties);
//
//        runtime.transition(authInstance, redirectInstance);
//
//        State headerState = stop.getStates().get("Header");
//        Map<String, Object> headerProperties = new HashMap<String, Object>();
//        headerProperties.put("name", "Location");
//        headerProperties.put("value", "http://test.com");
//        StateInstance headerInstance = headerState.buildInstance(headerProperties);
//        List headers = new ArrayList();
//        headers.add(headerInstance);
//
//        State responseState = stop.getStates().get("Response");
//        Map<String, Object> responseProperties = new HashMap<String, Object>();
//        responseProperties.put("content", "");
//        responseProperties.put("content_type", "");
//        responseProperties.put("status", 302);
//        responseProperties.put("headers", headers);
//        StateInstance responseInstance = responseState.buildInstance(responseProperties);
//
//        runtime.transition(redirectInstance, responseInstance);
//    }
//
//    @Test
//    public void transitionWithOptionals() throws Exception {
//        Stop stop = new Stop("./examples/kitchen-sink.stop");
//        assert(stop != null);
//
//        StopRuntime runtime = new StopRuntime(stop);
//
//        State renderState = stop.getStates().get("Request");
//        Enumeration methodEnumeration = renderState.getEnumerations().get("Method");
//        EnumerationInstance methodInstance = new EnumerationInstance(methodEnumeration, "GET");
//        Map<String, Object> requestProperties = new HashMap<String, Object>();
//        requestProperties.put("headers", new ArrayList());
//        requestProperties.put("parameters", new ArrayList());
//        requestProperties.put("method", methodInstance);
//        requestProperties.put("path", "/test");
//        requestProperties.put("time", Instant.now());
//        StateInstance requestInstance = renderState.buildInstance(requestProperties);
//
//        State routerState = stop.getStates().get("Router");
//        Map<String, Object> routerProperties = new HashMap<String, Object>();
//        routerProperties.put("request", requestInstance);
//        StateInstance routerInstance = routerState.buildInstance(routerProperties);
//
//        State loginState = stop.getStates().get("Login");
//        StateInstance loginInstance = loginState.buildInstance(new HashMap());
//
//        runtime.transition(routerInstance, loginInstance);
//    }
//
//    @Test
//    public void queue() throws Exception {
//        Stop stop = new Stop("./examples/kitchen-sink.stop");
//        assert(stop != null);
//
//        StopRuntime runtime = new StopRuntime(stop);
//
//        State renderState = stop.getStates().get("Request");
//        Enumeration methodEnumeration = renderState.getEnumerations().get("Method");
//        EnumerationInstance methodInstance = new EnumerationInstance(methodEnumeration, "GET");
//        Map<String, Object> requestProperties = new HashMap<String, Object>();
//        requestProperties.put("headers", new ArrayList());
//        requestProperties.put("parameters", new ArrayList());
//        requestProperties.put("method", methodInstance);
//        requestProperties.put("path", "/test");
//        requestProperties.put("time", Instant.now());
//        StateInstance requestInstance = renderState.buildInstance(requestProperties);
//
//        State routerState = stop.getStates().get("Router");
//        Map<String, Object> routerProperties = new HashMap<String, Object>();
//        routerProperties.put("request", requestInstance);
//        StateInstance routerInstance = routerState.buildInstance(routerProperties);
//
//        State postState = stop.getStates().get("Post");
//        Map<String, Object> postProperties = new HashMap<String, Object>();
//        postProperties.put("title", "the post");
//        postProperties.put("body", "the body");
//        postProperties.put("public", true);
//        StateInstance  postInstance = postState.buildInstance(postProperties);
//
//        State createState = stop.getStates().get("CreatePost");
//        Map<String, Object> createProperties = new HashMap<String, Object>();
//        createProperties.put("post", postInstance);
//        StateInstance createInstance = createState.buildInstance(createProperties);
//
//        runtime.transition(routerInstance, createInstance);
//
//        State pushState = stop.getStates().get("PushNotification");
//        Map<String, Object> pushProperties = new HashMap<String, Object>();
//        pushProperties.put("message", "a new post happened");
//        StateInstance pushInstance = pushState.buildInstance(pushProperties);
//
//        runtime.queue(createInstance, pushInstance);
//
//        assertThrows(StopRuntimeException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                runtime.transition(createInstance, pushInstance);
//            }
//        }, "should throw if transition to queue state");
//    }
//
//    @Test
//    public void timeoutTransition() throws Exception {
//        Stop stop = new Stop("./examples/kitchen-sink.stop");
//        assert(stop != null);
//
//        StopRuntime runtime = new StopRuntime(stop);
//
//        State getUserState = stop.getStates().get("GetUserWithPassword");
//        Map<String, Object> getUserStateProperties = new HashMap<String, Object>();
//        getUserStateProperties.put("login", "test");
//        getUserStateProperties.put("password", "passw0erd");
//        StateInstance getUserStateInstance = getUserState.buildInstance(getUserStateProperties);
//
//        State errorState = stop.getStates().get("UserNotFoundError");
//        Map<String, Object> errorProperties = new HashMap<String, Object>();
//        errorProperties.put("message", "couldn't find user");
//        StateInstance errorInstance = errorState.buildInstance(errorProperties);
//
//        runtime.transition(getUserStateInstance, errorInstance);
//
//        State timeoutState = stop.getStates().get("TimeoutError");
//        StateInstance timeoutInstance = timeoutState.buildInstance(new HashMap<>());
//
//        runtime.transition(getUserStateInstance, timeoutInstance);
//    }
}
