package com.tamchack.tamchack.exception;

import com.tamchack.tamchack.error.BasicException;
import com.tamchack.tamchack.error.ErrorCode;

public class ImageNotFoundException extends BasicException {

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }

}
