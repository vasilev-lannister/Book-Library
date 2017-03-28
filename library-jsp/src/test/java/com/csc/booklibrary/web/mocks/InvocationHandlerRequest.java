package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public final class InvocationHandlerRequest implements InvocationHandler {

    private final Map<String, String> parameters;
    private final Map<String, Object> attributes = new HashMap<>();
    private final HttpSession session;
    private final ServletContext context;

    public InvocationHandlerRequest(final Map<String, String> parameters) {
        this.parameters = parameters;
        session = (HttpSession) Proxy.newProxyInstance(HttpSession.class.getClassLoader(),
                new Class[] { HttpSession.class }, new InvocationHandlerSession());
        context = (ServletContext) Proxy.newProxyInstance(ServletContext.class.getClassLoader(),
                new Class[] { ServletContext.class }, new InvocationHandlerServletContext());
    }

    @SuppressWarnings("nls")
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("getParameter".equals(methodName)) {
            return parameters.get(args[0]);
        } else if ("getSession".equals(methodName)) {
            return session;
        } else if ("getServletContext".equals(methodName)) {
            return context;
        } else if ("getRequestDispatcher".equals(methodName)) {
            return Proxy.newProxyInstance(RequestDispatcher.class.getClassLoader(),
                    new Class[] { RequestDispatcher.class }, new InvocationHandlerRequestDispatcher((String) args[0]));
        } else if ("setAttribute".equals(methodName)) {
            attributes.put((String) args[0], args[1]);
        } else if ("getAttribute".equals(methodName)) {
            return attributes.get(args[0]);
        } else if ("getRequestURI".equals(methodName)) {
            return parameters.get("uri");
        } else if ("getQueryString".equals(methodName)) {
            return "id=123";
        } else {
            throw new IllegalStateException(String.valueOf(method));
        }
        return null;
    }

}
