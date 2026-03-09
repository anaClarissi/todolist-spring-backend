package com.anaclarissi.todolist_spring.service;

import com.anaclarissi.todolist_spring.model.Task;
import com.anaclarissi.todolist_spring.repository.TaskRepository;
import com.anaclarissi.todolist_spring.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Task update(Long id,Task task) {

        try {

            Task entity = repository.getReferenceById(id);

            updateDate(entity, task);

            return repository.save(entity);

        } catch (EntityNotFoundException e) {

            throw new ResourceNotFoundException(id);

        }

    }

    public void delete(Long id) {

        try {

            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {

            throw new ResourceNotFoundException(id);

        }

    }

    private void updateDate(Task entity, Task task) {

        entity.setName(task.getName());

        entity.setDescription(task.getDescription());

        entity.setEndDate(task.getEndDate());

        entity.setCompleted(task.isCompleted());

    }

}
