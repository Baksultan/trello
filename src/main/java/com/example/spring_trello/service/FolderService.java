package com.example.spring_trello.service;


import com.example.spring_trello.model.*;
import com.example.spring_trello.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final TaskCategoryService taskCategoryService;
    private final TaskService taskService;


    public List<Folder> getFolders() {
        return folderRepository.findAll();
    }

    public Folder getFolder(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    public void addFolder(Folder folder) {
        folderRepository.save(folder);
    }

    public void addTaskCategory(Long fId, Long cId) {
        Folder folder = folderRepository.findById(fId).orElse(null);
        TaskCategory category = taskCategoryService.getTaskCategory(cId);

        if (folder != null && category != null) {
            List<TaskCategory> categories = folder.getCategories();
            if (categories == null) {
                categories = new ArrayList<>();
            }
            categories.add(category);
            folder.setCategories(categories);
            folderRepository.save(folder);
        }
    }

    public List<Task> getTasks(Long folderId) {
        return taskService.getTasksByFolderId(folderId);
    }

    public Long deleteCategory(Long cId, Long fId) {

        Folder folder = folderRepository.findById(fId).orElse(null);
        TaskCategory category = taskCategoryService.getTaskCategory(cId);

        if (folder != null && category != null) {
            // Удаление категории из списка категорий папки
            folder.getCategories().remove(category);
            folderRepository.save(folder); // Сохранение обновленной информации о папке

            return fId;
        }

        return null;

    }

    public void deleteFolder(Long id) {
        Folder folder = getFolder(id);

        // Проверяем, что папка существует
        if (folder != null) {
            // Получаем список связанных задач
            List<Task> tasks = getTasks(id);

            // Удаляем связанные задачи
            for (Task task : tasks) {
                taskService.deleteTask(task.getId());
            }

            // Удаляем саму папку
            folderRepository.deleteById(id);
        }
    }





}
