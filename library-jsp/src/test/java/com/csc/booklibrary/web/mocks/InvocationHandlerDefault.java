package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class InvocationHandlerDefault implements InvocationHandler {

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return null;
    }

}
