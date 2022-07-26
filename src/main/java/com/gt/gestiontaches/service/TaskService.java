package com.gt.gestiontaches.service;

import com.gt.gestiontaches.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> search();
    void create (Task task);
    Task read(Long id);
    Task update(Task task, Long id);
    void delete(Long id);

    void employeeToTask(Long userId, Long taskId);
}
