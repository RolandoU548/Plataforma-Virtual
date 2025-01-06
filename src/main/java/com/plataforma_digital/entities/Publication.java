package com.plataforma_digital.entities;

public class Publication {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String state;
    private String createdAt;

    public Publication(int id, int userId, String title, String description, String state, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.state = state;
        this.createdAt = createdAt;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getState() {
        return state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}