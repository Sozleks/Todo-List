package ru.sozleks.todo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    public Task() {}

    public Task(String title) {
        this.title = title;
        this.status = TaskStatus.ACTIVE;
    }

    public Long getId() {return id;}
    public String getTitle() {return title;}
    public TaskStatus getStatus() {return status;}

    public void setId(Long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setStatus(TaskStatus status) {this.status = status;}
}
