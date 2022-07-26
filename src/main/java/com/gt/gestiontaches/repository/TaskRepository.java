package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
