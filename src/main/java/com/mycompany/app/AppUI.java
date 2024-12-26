package com.mycompany.app;

import javax.swing.*;

import java.awt.FlowLayout;

public class AppUI extends JFrame {
    private DatabaseConnection db;

    public AppUI() {
        setTitle("Portable App");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (db != null)
                    db.disconnect();
            }
        });
    }

    public static void main(String[] args) {
        AppUI app = new AppUI();

        // Importante Iniciar Base de Datos antes que los componentes
        app.initDatabase();
        app.initComponents();

        app.setVisible(true);
    }

    private void initDatabase() {
        db = new DatabaseConnection("database.db");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT, first_name text NOT NULL, last_name text NOT NULL, role text CHECK (role IN('Student','Professor','Support Personal')) NOT NULL, password text NOT NULL);");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS publications (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), title text NOT NULL, description text, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS comments (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), publication_id integer NOT NULL references publications (id),text text NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
    }

    private void initComponents() {
        RegisterForm registerForm = new RegisterForm(db);
        add(registerForm);
    }
}
