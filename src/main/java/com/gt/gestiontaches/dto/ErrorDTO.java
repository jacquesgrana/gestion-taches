package com.gt.gestiontaches.dto;

import com.gt.gestiontaches.enums.ErrorCode;

public class ErrorDTO {

    private ErrorCode code;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
