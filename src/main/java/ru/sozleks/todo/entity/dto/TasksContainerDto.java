package ru.sozleks.todo.entity.dto;

import ru.sozleks.todo.entity.Task;

import java.util.List;

public class TasksContainerDto {
    private final List<Task> tasks;
    private final long numberOfAllTasks;
    private final long numberOfActiveTasks;
    private final long numberOfDoneTasks;

    public TasksContainerDto(List<Task> tasks, long numberOfAllTasks, long numberOfActiveTasks, long numberOfDoneTasks) {
        this.tasks = tasks;
        this.numberOfAllTasks = numberOfAllTasks;
        this.numberOfActiveTasks = numberOfActiveTasks;
        this.numberOfDoneTasks = numberOfDoneTasks;
    }

    public List<Task> getTasks() {return tasks;}
    public long getNumberOfAllTasks() {return numberOfAllTasks;}
    public long getNumberOfActiveTasks() {return numberOfActiveTasks;}
    public long getNumberOfDoneTasks() {return numberOfDoneTasks;}
}
