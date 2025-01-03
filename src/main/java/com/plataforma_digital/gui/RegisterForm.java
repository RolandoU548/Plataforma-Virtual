package com.plataforma_digital.gui;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.User;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class RegisterForm extends JPanel {
    private AppUI appUI;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JLabel usernameLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel roleLabel;
    private JLabel passwordLabel;
    private JTextField usernameTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JComboBox<String> roleTextField;
    private JPasswordField passwordTextField;
    private JButton registerButton;
    private JButton loginButton;

    public RegisterForm(AppUI appUI) {
        this.appUI = appUI;

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());

        panel5 = new JPanel();
        panel5.setLayout(new FlowLayout());

        usernameLabel = new JLabel("Username");
        usernameLabel.setSize(80, 25);
        usernameTextField = new JTextField(20);
        usernameTextField.setSize(165, 25);
        panel1.add(usernameLabel);
        panel1.add(usernameTextField);

        firstNameLabel = new JLabel("Firstname");
        firstNameLabel.setSize(80, 25);
        firstNameTextField = new JTextField(20);
        firstNameTextField.setSize(165, 25);
        panel2.add(firstNameLabel);
        panel2.add(firstNameTextField);

        lastNameLabel = new JLabel("Lastname");
        lastNameLabel.setSize(80, 25);
        lastNameTextField = new JTextField(20);
        lastNameTextField.setSize(165, 25);
        panel3.add(lastNameLabel);
        panel3.add(lastNameTextField);

        roleLabel = new JLabel("Role");
        roleLabel.setSize(80, 25);
        roleTextField = new JComboBox<String>();
        roleTextField.addItem("Student");
        roleTextField.addItem("Professor");
        roleTextField.addItem("Support Personal");
        panel4.add(roleLabel);
        panel4.add(roleTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        passwordTextField = new JPasswordField(20);
        passwordTextField.setSize(165, 25);
        panel5.add(passwordLabel);
        panel5.add(passwordTextField);

        registerButton = new JButton("Register");
        registerButton.setSize(80, 25);

        registerButton.addActionListener(e -> {
            if (validateFields()) {
                register();
                clearFields();
            }
        });

        loginButton = new JButton("Login");
        loginButton.setSize(80, 25);

        loginButton.addActionListener(e -> {
            appUI.showPanel("loginForm");
            clearFields();
        });

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(registerButton);
        add(loginButton);
    }

    private void clearFields() {
        usernameTextField.setText("");
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        roleTextField.setSelectedIndex(0);
        passwordTextField.setText("");
    }

    private boolean validateFields() {
        if (usernameTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty()
                || lastNameTextField.getText().isEmpty()
                || passwordTextField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Fields Required",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void register() {
        String username = usernameTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = new String(passwordTextField.getPassword());
        String role = roleTextField.getSelectedItem().toString();
        if (DatabaseConnection.getUserByUsername(username) != null) {
            System.out.println("User was not registered because the username is already in use");
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.",
                    "Username already in used",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        User user = new User(0, username, firstName, lastName, role, password);
        DatabaseConnection.createUser(user);
        System.out.println("User registered with ID: " + user.getId());
        JOptionPane.showMessageDialog(null, "User '" + username + "' created succesfully, please log in.",
                "User created",
                JOptionPane.INFORMATION_MESSAGE);
        appUI.showPanel("loginForm");
    }
}
