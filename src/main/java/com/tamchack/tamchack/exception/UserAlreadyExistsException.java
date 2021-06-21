package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class UserAlreadyExistsException extends BasicException {

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }

}
