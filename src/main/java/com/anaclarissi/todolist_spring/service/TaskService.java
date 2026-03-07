package com.anaclarissi.todolist_spring.service;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> listAll() {

        return repository.findAll();

    }

    public Task create(Task task) {

        if (task.getEndDate().isBefore(task.getStartDate())) {

            throw new IllegalArgumentException("Erro: The end date cannot be earlier than the start date.");

        }

        return repository.save(task);

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

}
