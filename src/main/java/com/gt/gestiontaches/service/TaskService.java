package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Task;
import com.gt.gestiontaches.exceptions.BadRequestException;

import java.util.List;

public interface TaskService {

    List<Task> search();
    void create (Task task);
    Task read(Long id) throws BadRequestException;
    Task update(Task task, Long id) throws BadRequestException;
    void delete(Long id);

    void employeeToTask(Long userId, Long taskId);
}
