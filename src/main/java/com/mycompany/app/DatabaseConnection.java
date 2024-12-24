package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    public static Connection connect() {
        String url = "jdbc:sqlite:your_database_file.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            createTable(conn); // Create table if it doesn't exist
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "username text NOT NULL"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Table is ready.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
