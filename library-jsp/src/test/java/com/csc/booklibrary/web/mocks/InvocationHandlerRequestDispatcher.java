package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class InvocationHandlerRequestDispatcher implements InvocationHandler {

    private String path;

    public InvocationHandlerRequestDispatcher(final String path) {
        this.path = path;
    }

    @SuppressWarnings("nls")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("forward".equals(methodName)) {
            return path;
        } else {
            throw new IllegalStateException(String.valueOf(method));
        }
    }

}
