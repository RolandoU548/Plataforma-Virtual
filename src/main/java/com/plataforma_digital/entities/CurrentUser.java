package com.plataforma_digital.entities;

public class CurrentUser {
    private static int id;
    private static String firstName;
    private static String lastName;
    private static String username;
    private static String role;
    private static String password;

    public static void setUser(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        username = user.getUsername();
        role = user.getRole();
        password = user.getPassword();
    }

    public static int getId() {
        return id;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static String getPassword() {
        return password;
    }

    public static void clear() {
        id = 0;
        firstName = null;
        lastName = null;
        username = null;
        role = null;
        password = null;
    }
}
