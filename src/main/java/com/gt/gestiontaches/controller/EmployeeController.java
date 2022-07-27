package com.gt.gestiontaches.controller;

import com.gt.gestiontaches.dto.EmployeeTaskDTO;
import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path="all")
    public List<Employee> search() {
        return this.employeeService.search();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Employee employee) throws BadRequestException {
        this.employeeService.create(employee);
    }

    @GetMapping(path = "{id}")
    public Employee read(@PathVariable("id") Long id) throws BadRequestException {
        return this.employeeService.read(id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        this.employeeService.delete(id);
    }

    @PutMapping(path = "{id}")
    public Employee update(@RequestBody Employee employee, @PathVariable("id") Long id) throws BadRequestException {
        return this.employeeService.update(employee, id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{employeeId}/task")
    public void taskToEmployee(@Validated @RequestBody EmployeeTaskDTO EmployeeTask) throws BadRequestException {
        this.employeeService.taskToEmployee(EmployeeTask.getIdTask(), EmployeeTask.getIdEmployee());
    }
}
