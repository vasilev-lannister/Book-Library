package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class InvocationHandlerFilterChain implements InvocationHandler {

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if ("doFilter".equals(method.getName())) {
            ((HttpServletRequest) args[0]).setAttribute("chain.doFilter()Executed", true);
        }
        return null;
    }

}
