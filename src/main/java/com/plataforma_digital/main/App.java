package com.plataforma_digital.main;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.gui.AppUI;

public class App {

    public static void main(String[] args) {
        App.initDatabase();
        App.initAppUI();
    }

    private static void initDatabase() {
        DatabaseConnection.connect("database.db");
        DatabaseConnection.createTables();
    }

    private static void initAppUI() {
        AppUI appUI = new AppUI();
        appUI.setVisible(true);
    }
}
