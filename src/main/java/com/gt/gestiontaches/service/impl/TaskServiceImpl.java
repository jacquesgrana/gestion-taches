package com.gt.gestiontaches.service.impl;

import com.gt.gestiontaches.entity.Employee;
import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.repository.TaskRepository;
import com.gt.gestiontaches.service.EmployeeService;
import com.gt.gestiontaches.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    //@Autowired
    //private EmployeeService employeeService;

    public TaskServiceImpl() {
    }

    /*
    public TaskServiceImpl(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }*/

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> search() {
        return this.taskRepository.findAll();
    }

    @Override
    public void create(Task task) {
        // verif TI > TR
        // verif TR =
        task.setRt(task.getIt());
        this.taskRepository.save(task);
    }

    @Override
    public Task read(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task update(Task task, Long id) {
        Task current = this.read(id);
        current.setTitle(task.getTitle());
        current.setDescription(task.getDescription());
        current.setIt(task.getIt());
        current.setRt(task.getRt());
        taskRepository.save(current); // *******************************************************
        return current;
    }

    @Override
    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }


    @Override
    public void employeeToTask(Long employeeId, Long taskId) {
/*
        Task currentTask = this.read(taskId);
        Employee currentEmpl = employeeService.read(employeeId);
        currentTask.getEmployees().add(currentEmpl);
        taskRepository.save(currentTask);*/
    }


}
