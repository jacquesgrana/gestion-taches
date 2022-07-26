package com.gt.gestiontaches.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTaskDTO {

    @NotNull
    private Integer idEmployee;
    @NotNull
    private Integer idTask;


}
