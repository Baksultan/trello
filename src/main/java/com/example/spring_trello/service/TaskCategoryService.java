package com.example.spring_trello.service;


import com.example.spring_trello.model.TaskCategory;
import com.example.spring_trello.repository.TaskCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskCategoryService {

    private final TaskCategoryRepository taskCategoriesRepository;

    public List<TaskCategory> getTaskCategories() {
        return taskCategoriesRepository.findAll();
    }

    public List<TaskCategory> getTaskCategoriesByFolderId(Long folderId) {
        return null;
    }

    public TaskCategory getTaskCategory(Long id) {
        return taskCategoriesRepository.findById(id).orElse(null);
    }

}
