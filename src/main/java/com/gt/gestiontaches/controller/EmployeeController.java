package com.gt.gestiontaches.controller;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void create(@RequestBody Employee employee) {
        this.employeeService.create(employee);
    }
}
