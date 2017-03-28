package com.csc.booklibrary.services.impl;

import com.csc.booklibrary.persistence.interfaces.TransactionHandler;
import com.csc.booklibrary.persistence.interfaces.UserRepository;
import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.LoginService;

public final class LoginServiceImpl implements LoginService {

    private final TransactionHandler transactionHandler;
    private final LoginServiceImplLogic logic = new LoginServiceImplLogic();

    public LoginServiceImpl(final TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Override
    public UserDTO performLogin(final String username, final String password) {
        return transactionHandler.execute(
                serviceFactory -> logic.login(username, password, serviceFactory.findService(UserRepository.class)));
    }
}
