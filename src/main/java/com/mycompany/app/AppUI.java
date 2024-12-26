package com.mycompany.app;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                db.disconnect();
            }
        });
    }

    public static void main(String[] args) {
        AppUI app = new AppUI();
        app.initComponents();
        app.setVisible(true);
        app.initDatabase();
    }

    private void initDatabase() {
        db = new DatabaseConnection("database.db");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY AUTOINCREMENT, first_name text NOT NULL, last_name text NOT NULL, role text CHECK (role IN('Student','Professor','Support Personal')) NOT NULL, password text NOT NULL);");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS comments (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), text text NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        db.executeStatement(
                "CREATE TABLE IF NOT EXISTS publications (id integer PRIMARY KEY AUTOINCREMENT, user_id integer NOT NULL references users (id), title text NOT NULL, description text, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
    }

    private void initComponents() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setSize(80, 25);
        panel1.add(firstNameLabel);
        JTextField firstNameText = new JTextField(20);
        firstNameText.setSize(165, 25);
        panel1.add(firstNameText);

        JLabel lastNameLabel = new JLabel("LastName");
        lastNameLabel.setSize(80, 25);
        panel2.add(lastNameLabel);
        JTextField lastNameText = new JTextField(20);
        lastNameText.setSize(165, 25);
        panel2.add(lastNameText);

        JLabel roleLabel = new JLabel("Role");
        roleLabel.setSize(80, 25);
        panel3.add(roleLabel);
        JComboBox<String> roleText = new JComboBox<String>();
        roleText.addItem("Student");
        roleText.addItem("Professor");
        roleText.addItem("Support Persoal");
        panel3.add(roleText);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        panel4.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setSize(165, 25);
        panel4.add(passwordText);

        JButton loginButton = new JButton("Register");
        loginButton.setSize(80, 25);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String password = passwordText.getPassword().toString();
                String role = roleText.getSelectedItem().toString();
                String sql = "INSERT INTO users(first_name, last_name, role, password) VALUES(?, ?, ?, ?)";
                db.executePreparedStatement(sql, firstName, lastName, role, password);
            }
        });
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(loginButton);
    }
}
