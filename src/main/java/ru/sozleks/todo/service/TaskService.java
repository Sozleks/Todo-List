package ru.sozleks.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sozleks.todo.entity.Task;
import ru.sozleks.todo.entity.TaskStatus;
import ru.sozleks.todo.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findTasksByFilter(String filter) {
        if("active".equalsIgnoreCase(filter)) return taskRepository.findByStatus(TaskStatus.ACTIVE);
        else if("done".equalsIgnoreCase(filter)) return taskRepository.findByStatus(TaskStatus.DONE);
        else return taskRepository.findAll();
    }

    public void saveTask(String title) {
        taskRepository.save(new Task(title));
    }

    public void updateTaskStatus(Long id, TaskStatus newStatus) {
        taskRepository.findById(id).ifPresent(task -> {
                    task.setStatus(newStatus);
                    taskRepository.save(task);
                });
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public long countAllTasks() {return (long) taskRepository.count();}
    public long countActiveTasks() {return taskRepository.countByStatus(TaskStatus.ACTIVE);}
    public long countDoneTasks() {return taskRepository.countByStatus(TaskStatus.DONE);}
}
