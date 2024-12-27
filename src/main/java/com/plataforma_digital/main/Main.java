package com.plataforma_digital.main;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.gui.AppUI;

public class Main {

    public static void main(String[] args) {
        Main.initDatabase();
        Main.initAppUI();
    }

    private static void initDatabase() {
        DatabaseConnection.connect("database.db");
        DatabaseConnection.executeStatement(
                "CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT, first_name text NOT NULL, last_name text NOT NULL, role text CHECK (role IN('Student','Professor','Support Personal')) NOT NULL, password text NOT NULL);");
        DatabaseConnection.executeStatement(
                "CREATE TABLE IF NOT EXISTS publications (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), title text NOT NULL, description text, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        DatabaseConnection.executeStatement(
                "CREATE TABLE IF NOT EXISTS comments (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), publication_id integer NOT NULL references publications (id),text text NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
    }

    private static void initAppUI() {
        AppUI appUI = new AppUI();
        appUI.setVisible(true);
    }
}
