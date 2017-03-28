package com.csc.booklibrary.web.mocks;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.csc.booklibrary.common.ServiceFactory;
import com.csc.booklibrary.common.ServiceRegistry;
import com.csc.booklibrary.services.interfaces.BookRequestService;
import com.csc.booklibrary.services.interfaces.BookService;
import com.csc.booklibrary.services.interfaces.BorrowBookService;
import com.csc.booklibrary.services.interfaces.LoginService;
import com.csc.booklibrary.services.interfaces.MessagesService;

public final class InvocationHandlerServletContext implements InvocationHandler {

    private final TransactionHandlerMock handler = new TransactionHandlerMock();
    private final ServiceRegistry<TransactionHandlerMock> registry = new ServiceRegistry<>();

    @SuppressWarnings("nls")
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String methodName = method.getName();
        if ("getAttribute".equals(methodName)) {
            registry.registerService(MessagesService.class, MessagesServiceMock::new);
            registry.registerService(LoginService.class, LoginServiceMock::new);
            registry.registerService(BookService.class, BookServiceMock::new);
            registry.registerService(BookRequestService.class, BookRequestServiceMock::new);
            registry.registerService(BorrowBookService.class, BorrowBookServiceMock::new);
            return new ServiceFactory<>(registry, handler);
        } else {
            throw new IllegalStateException(String.valueOf(method));
        }
    }

}
