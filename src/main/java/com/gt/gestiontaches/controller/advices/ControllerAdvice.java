package com.gt.gestiontaches.controller.advices;

import com.gt.gestiontaches.dto.ErrorDTO;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public @ResponseBody ErrorDTO handleIllegalArgumentException(Exception exception) {
        //exception.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(ErrorCode.USERNAME_ALREADY_EXISTS);
        errorDTO.setMessage(exception.getMessage());
        return errorDTO;
    }
}
