package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerResponse implements InvocationHandler {
    private int status = 0;

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("sendRedirect".equals(methodName)) {
            status = 202;
        } else if ("getStatus".equals(methodName)) {
            return status;
        }
        return null;
    }

}
