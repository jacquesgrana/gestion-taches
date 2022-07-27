package com.gt.gestiontaches.repository;

import com.gt.gestiontaches.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
