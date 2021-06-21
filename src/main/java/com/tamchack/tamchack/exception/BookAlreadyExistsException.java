package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class BookAlreadyExistsException extends BasicException {

    public BookAlreadyExistsException() {
        super(ErrorCode.BOOK_ALREADY_EXISTS);
    }

}
