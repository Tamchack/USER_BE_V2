package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class NotAuthorizedException extends BasicException {

    public NotAuthorizedException(ErrorCode errorCode) {
        super(ErrorCode.INVALID_TOKEN);
    }
}
