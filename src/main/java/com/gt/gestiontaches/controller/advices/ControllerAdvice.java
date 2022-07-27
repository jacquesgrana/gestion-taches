package com.gt.gestiontaches.controller.advices;

import com.gt.gestiontaches.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public @ResponseBody ErrorDTO handleIllegalArgumentException(Exception exception) {
        //exception.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode("ERR_EMAIL_ALREADY_USED");
        errorDTO.setMessage(exception.getMessage());
        return errorDTO;
    }
}
