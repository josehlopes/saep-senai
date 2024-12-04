package com.example.saep.database;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Entity
@Table(name = "app_task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private UserEntity user;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private Integer priority;

    @Column(name = "register_in", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime registerIn;

    @Column(nullable = false)
    private Integer status;

    public TaskEntity(Integer id, UserEntity user, String description, String sector, Integer priority, LocalDateTime registerIn, Integer status) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.sector = sector;
        this.priority = priority;
        this.registerIn = registerIn;
        this.status = status;
    }

    public TaskEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getRegisterIn() {
        return registerIn;
    }

    public void setRegisterIn(LocalDateTime registerIn) {
        this.registerIn = registerIn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
