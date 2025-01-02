package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.User;
import com.plataforma_digital.entities.CurrentUser;

public class LoginForm extends JPanel {
    private AppUI appUI;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
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
        usernameTextField = new JTextField(20);
        usernameTextField.setSize(165, 25);

        panel1.add(usernameLabel);
        panel1.add(usernameTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        passwordTextField = new JPasswordField(20);
        passwordTextField.setSize(165, 25);

        panel2.add(passwordLabel);
        panel2.add(passwordTextField);

        loginButton = new JButton("Login");
        loginButton.setSize(80, 25);

        loginButton.addActionListener(e -> {
            if (validateFields()) {
                login();
                clearFields();
            }
        });

        registerButton = new JButton("Register");
        registerButton.setSize(80, 25);

        registerButton.addActionListener(e -> {
            appUI.showPanel("registerForm");
            clearFields();
        });

        add(panel1);
        add(panel2);
        add(loginButton);
        add(registerButton);
    }

    private void clearFields() {
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    private boolean validateFields() {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Fields Required",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void login() {
        String username = usernameTextField.getText();
        String password = new String(passwordTextField.getPassword());
        User user = DatabaseConnection.authenticateUser(username, password);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
            return;
        }
        CurrentUser.setUser(user);
        appUI.home.editProfile.updateCurrentUserInfo();
        System.out.println("User " + username + " logged in");
        appUI.showPanel("home");
        JOptionPane.showMessageDialog(null, "Welcome, " + username + "!", "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
