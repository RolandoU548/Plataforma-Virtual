package com.plataforma_digital.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection conn;

    public static void connect(String databaseUrlString) {
        String url = "jdbc:sqlite:" + databaseUrlString;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void executeStatement(String sql) {
        if (conn == null) {
            System.out.println("Connection to database is null");
            return;
        }
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Statement executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executePreparedStatement(String sql, String... params) {
        if (conn == null) {
            System.out.println("Connection to database is null");
            return;
        }
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

    public static ResultSet executeSelectStatement(String sql) {
        ResultSet rs = null;
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                rs = stmt.executeQuery(sql);
                System.out.println("SelectStatement executed");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Connection to database is null");
        }
        return rs;
    }
}
