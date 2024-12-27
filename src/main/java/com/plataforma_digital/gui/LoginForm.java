package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.plataforma_digital.database.DatabaseConnection;

public class LoginForm extends JPanel {
    private AppUI appUI;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton registerButton;

    public LoginForm(AppUI appUI) {
        this.appUI = appUI;
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username");
        usernameLabel.setSize(80, 25);
        usernameText = new JTextField(20);
        usernameText.setSize(165, 25);

        panel1.add(usernameLabel);
        panel1.add(usernameText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        passwordText = new JPasswordField(20);
        passwordText.setSize(165, 25);

        panel2.add(passwordLabel);
        panel2.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setSize(80, 25);

        loginButton.addActionListener(e -> {
            login();
            clearFields();
        });

        registerButton = new JButton("Register");
        registerButton.setSize(80, 25);

        registerButton.addActionListener(e -> appUI.showPanel("registerForm"));

        add(panel1);
        add(panel2);
        add(loginButton);
        add(registerButton);
    }

    public void clearFields() {
        usernameText.setText("");
        passwordText.setText("");
    }

    public void login() {
        String username = usernameText.getText();
        String password = new String(passwordText.getPassword());
        String sql = "SELECT * FROM users WHERE first_name = ? AND password = ?";
        try (ResultSet rs = DatabaseConnection.executePreparedSelectStatement(sql, username, password)) {
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Bienvenido, " + username + "!");
                appUI.showPanel("home");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
