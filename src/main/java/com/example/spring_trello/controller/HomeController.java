package com.example.spring_trello.controller;



import com.example.spring_trello.model.*;
import com.example.spring_trello.service.FolderService;
import com.example.spring_trello.service.TaskCategoryService;
import com.example.spring_trello.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final FolderService folderService;
    private final TaskCategoryService taskCategoryService;
    private final TaskService taskService;


    @GetMapping(value = "/")
    public String indexPage(Model model) {

        model.addAttribute("folders", folderService.getFolders());

        return "index";
    }

    @PostMapping(value = "add-folder")
    public String addNewFolder(Folder folder) {
        folderService.addFolder(folder);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{folderId}")
    public String folderDetails(@PathVariable(name = "folderId") Long id, Model model){

        Folder folder = folderService.getFolder(id);
        model.addAttribute("folder", folder);

        model.addAttribute("tasks", taskService.getTasksByFolderId(id));

        List<TaskCategory> categoryList = taskCategoryService.getTaskCategories();
        categoryList.removeAll(folder.getCategories());
        model.addAttribute("categories", categoryList);


        return "details";
    }

    @GetMapping(value = "/view/{taskId}")
    public String taskView(@PathVariable(name = "taskId") Long id, Model model){

        model.addAttribute("task", taskService.getTask(id));

        return "view";
    }

    @PostMapping(value = "/add-task")
    public String addTask(Task task, @RequestParam("f_id") Long id) {
        taskService.addTask(task);
        return "redirect:/details/" + id;
    }


    @PostMapping(value = "/add-category")
    public String addCategory(@RequestParam("f_id") Long fId,
                              @RequestParam("c_id") Long cId) {
        folderService.addTaskCategory(fId, cId);
        return "redirect:/details/" + fId;
    }

    @PostMapping(value = "/update-task")
    public String updateTask(Task task) {
        taskService.updateTask(task);
        return "redirect:/view/" + task.getId();
    }

    @PostMapping(value = "/delete-task")
    public String deleteTask(@RequestParam("id") Long id) {

        return "redirect:/details/" + taskService.deleteTask(id);
    }

    @PostMapping(value = "/delete-category")
    public String deleteTask(@RequestParam("category_id") Long cId,
                             @RequestParam("folder_id") Long fId) {

        return "redirect:/details/" + folderService.deleteCategory(cId, fId);
    }

    @PostMapping(value = "/delete-folder")
    public String deleteFolder(@RequestParam("id") Long id) {
        folderService.deleteFolder(id);
        return "redirect:/";
    }

}
