package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection conn;

    public DatabaseConnection(String databaseUrlString) {
        String url = "jdbc:sqlite:" + databaseUrlString;
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void executeStatement(String sql) {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Statement executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executePreparedStatement(String sql, String... params) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            pstmt.executeUpdate();
            System.out.println("PreparedStatement executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet executeSelectStatement(String sql) {
        ResultSet rs = null;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
            System.out.println("SelectStatement executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
