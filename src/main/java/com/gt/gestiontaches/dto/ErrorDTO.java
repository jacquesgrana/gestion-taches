package com.gt.gestiontaches.dto;

import com.gt.gestiontaches.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private ErrorCode code;
    private String message;

}
