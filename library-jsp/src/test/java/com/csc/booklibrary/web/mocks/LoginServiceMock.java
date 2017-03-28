package com.csc.booklibrary.web.mocks;

import com.csc.booklibrary.services.dto.UserDTO;
import com.csc.booklibrary.services.interfaces.LoginService;

public final class LoginServiceMock implements LoginService {

    LoginServiceMock(final TransactionHandlerMock handler) {

    }

    @SuppressWarnings("nls")
    @Override
    public UserDTO performLogin(final String username, final String password) {
        return new UserDTO(-5, username, "first", "name", null, null, null, "e@mail.com", "036147258");
    }

}
