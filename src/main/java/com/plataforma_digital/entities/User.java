package com.plataforma_digital.entities;

import com.plataforma_digital.utils.StringUtils;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;
    private String password;

    public User(int id, String username, String firstName, String lastName, String role, String password) {
        this.id = id;
        this.username = username;
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.role = role;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = StringUtils.capitalize(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = StringUtils.capitalize(lastName);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
