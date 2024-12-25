package com.mycompany.app;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Portable App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
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
                Connection conn = DatabaseConnection.connect();
                try {
                    String sql = "INSERT INTO users(username) VALUES(?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, user);
                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
}
