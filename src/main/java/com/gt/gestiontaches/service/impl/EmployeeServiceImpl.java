package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.enums.ErrorCode;
import com.gt.gestiontaches.exceptions.BadRequestException;
import com.gt.gestiontaches.repository.EmployeeRepository;
import com.gt.gestiontaches.repository.TaskRepository;
import com.gt.gestiontaches.service.EmployeeService;
import com.gt.gestiontaches.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
//@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskService taskService;

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    @Override
    public List<Employee> search(String query) {
        if (query == null) {
            return this.employeeRepository.findAll();
        }
        return this.employeeRepository.search(query).collect(Collectors.toList());

    }

    @Override
    public void create(Employee employee) throws BadRequestException {
        log.info("Création de l'employé {} / {}", employee.getFirstName(), employee.getLastName());
        Optional<Employee> employeeOptional = this.employeeRepository.findByUserName(employee.getUsername());
        if(employeeOptional.isPresent()) {
            throw new BadRequestException(ErrorCode.USERNAME_ALREADY_EXISTS, "Un employé existe déjà avec cet username");
        }
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee read(Integer id) throws BadRequestException {
        return this.employeeRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorCode.EMPLOYEE_NOT_FOUND, "Pas d'employé a cet id"));
    }

    @Override
    public Employee update(Employee employee, Integer id) throws BadRequestException {
        Employee current = this.read(id);
        current.setFirstName(employee.getFirstName());
        current.setLastName(employee.getLastName());
        employeeRepository.save(current); // *****************************************
        return current;
    }

    @Override
    public void delete(Integer id) throws BadRequestException {
        Employee employeeToDelete = this.read(id);
        this.employeeRepository.delete(employeeToDelete);
    }

    @Override
    public void taskToEmployee(Integer taskId, Integer employeeId) throws BadRequestException {
        Employee currentEmpl = this.read(employeeId);
        Task currentTask = taskService.read(taskId);
        //currentEmpl.getTasks().add(currentTask);
        //currentTask.getEmployees().add(currentEmpl);
        List<Task> tasks;

        if(currentEmpl.getTasks() == null) {
            tasks = new ArrayList<>();
        }
        else {
            Optional<Task> existingTask = currentEmpl.getTasks().stream().filter(u -> u.getId() == taskId).findFirst();
            if (existingTask.isPresent()) {
                throw new BadRequestException(ErrorCode.TASK_ALREADY_EXISTS, "Cette tache est déjà attribuée");
            }
            currentEmpl.getTasks().add(currentTask);
            employeeRepository.save(currentEmpl); // ********************************
        }
    }

    @Override
    public Employee getByUserName(String username) throws BadRequestException {
        return this.employeeRepository
                .findByUserName(username)
                .orElseThrow(()-> new BadRequestException(ErrorCode.EMPLOYEE_NOT_FOUND, "Aucun employé trouvé"));
    }
}
