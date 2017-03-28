package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.ServletContext;

public class InvocationHandlerServletConfig implements InvocationHandler {
    private final ServletContext context;

    public InvocationHandlerServletConfig() {
        context = (ServletContext) Proxy.newProxyInstance(ServletContext.class.getClassLoader(),
                new Class[] { ServletContext.class }, new InvocationHandlerServletContext());
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("getServletContext".equals(methodName)) {
            return context;
        }
        return null;
    }

}
