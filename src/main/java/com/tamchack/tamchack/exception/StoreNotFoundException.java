package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class StoreNotFoundException extends BasicException {

    public StoreNotFoundException() {
        super(ErrorCode.STORE_NOT_FOUND);
    }

}
