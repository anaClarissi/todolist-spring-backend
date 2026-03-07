package com.anaclarissi.todolist_spring.repository;

import com.anaclarissi.todolist_spring.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
