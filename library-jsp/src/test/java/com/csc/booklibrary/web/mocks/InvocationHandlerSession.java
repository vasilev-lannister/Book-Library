package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class InvocationHandlerSession implements InvocationHandler {

    private final Map<String, Object> attributes = new HashMap<>();

    @SuppressWarnings("nls")
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("setAttribute".equals(methodName)) {
            attributes.put((String) args[0], args[1]);
        } else if ("getAttribute".equals(methodName)) {
            return attributes.get(args[0]);
        } else {
            throw new IllegalStateException(String.valueOf(method));
        }
        return null;
    }

}
