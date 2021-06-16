package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class BookNotFoundException extends BasicException {

    public BookNotFoundException() {
        super(ErrorCode.BOOK_NOT_FOUND);
    }

}
