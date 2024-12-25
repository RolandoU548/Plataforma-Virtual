package com.mycompany.app;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppUI {
    private static DatabaseConnection db;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Portable App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);

        db = new DatabaseConnection();
        db.connect("database.db");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT, username text NOT NULL);");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                db.disconnect();
            }
        });
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(new FlowLayout());

        JLabel userLabel = new JLabel("User");
        userLabel.setSize(80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setSize(165, 25);
        panel.add(userText);

        JButton loginButton = new JButton("login");
        loginButton.setSize(80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String sql = "INSERT INTO users(username) VALUES(?)";
                db.executePreparedStatement(sql, user);
            }
        });
    }
}
