package com.gt.gestiontaches.exceptions;

import com.gt.gestiontaches.enums.ErrorCode;

public class BadRequestException extends Exception{

    private ErrorCode code;
    public BadRequestException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
}
