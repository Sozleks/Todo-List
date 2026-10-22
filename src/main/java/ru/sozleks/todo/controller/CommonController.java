package ru.sozleks.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.sozleks.todo.entity.TaskStatus;
import ru.sozleks.todo.entity.dto.TasksContainerDto;
import ru.sozleks.todo.service.TaskService;

@Controller
public class CommonController {

    private final TaskService taskService;

    @Autowired
    public CommonController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String redirectToMainPage() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String getMainPage(Model model, @RequestParam(name = "filter", required = false) String filterParam) {
        TasksContainerDto container = new TasksContainerDto(
                taskService.findTasksByFilter(filterParam),
                taskService.countAllTasks(),
                taskService.countActiveTasks(),
                taskService.countDoneTasks()
        );

        model.addAttribute("filter", filterParam);
        model.addAttribute("numberOfAllTasks", container.getNumberOfAllTasks());
        model.addAttribute("numberOfActiveTasks", container.getNumberOfActiveTasks());
        model.addAttribute("numberOfDoneTasks", container.getNumberOfDoneTasks());
        model.addAttribute("tasks", container.getTasks());

        return "main-page";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam String title) {
        if (title != null && !title.isBlank()) {
            taskService.saveTask(title);
        }

        return "redirect:/main";
    }

    @PostMapping("/done-task")
    public String makeTaskDone(@RequestParam Long id,
                               @RequestParam(name = "filter", required = false) String filterParam) {
        taskService.updateTaskStatus(id, TaskStatus.DONE);


        return "redirect:/main" + (filterParam != null && !filterParam.isBlank() ? "?filter=" + filterParam : "");
    }

    @PostMapping("/delete-task")
    public String deleteTask(@RequestParam Long id,
                             @RequestParam(name = "filter", required = false) String filterParam) {
        taskService.deleteTask(id);

        return "redirect:/main" + (filterParam != null && !filterParam.isBlank() ? "?filter=" + filterParam : "");
    }
}
