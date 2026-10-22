package ru.sozleks.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sozleks.todo.entity.Task;
import ru.sozleks.todo.entity.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    long countByStatus(TaskStatus status);
}
