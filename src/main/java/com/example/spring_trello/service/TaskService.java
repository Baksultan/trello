package com.example.spring_trello.service;


import com.example.spring_trello.model.*;
import com.example.spring_trello.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public List<Task> getTasksByFolderId(Long id) {
        return taskRepository.findByFolderId(id);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public Long deleteTask(Long id) {
        Long resultId = getTask(id).getFolder().getId();
        taskRepository.deleteById(id);
        return resultId;
    }




}
