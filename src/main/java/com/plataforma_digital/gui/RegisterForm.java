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
    private JTextField usernameText;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> roleText;
    private JPasswordField passwordText;
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
        usernameText = new JTextField(20);
        usernameText.setSize(165, 25);
        panel1.add(usernameLabel);
        panel1.add(usernameText);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setSize(80, 25);
        firstNameText = new JTextField(20);
        firstNameText.setSize(165, 25);
        panel2.add(firstNameLabel);
        panel2.add(firstNameText);

        lastNameLabel = new JLabel("LastName");
        lastNameLabel.setSize(80, 25);
        lastNameText = new JTextField(20);
        lastNameText.setSize(165, 25);
        panel3.add(lastNameLabel);
        panel3.add(lastNameText);

        roleLabel = new JLabel("Role");
        roleLabel.setSize(80, 25);
        roleText = new JComboBox<String>();
        roleText.addItem("Student");
        roleText.addItem("Professor");
        roleText.addItem("Support Personal");
        panel4.add(roleLabel);
        panel4.add(roleText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        passwordText = new JPasswordField(20);
        passwordText.setSize(165, 25);
        panel5.add(passwordLabel);
        panel5.add(passwordText);

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

        loginButton.addActionListener(e -> appUI.showPanel("loginForm"));

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(registerButton);
        add(loginButton);
    }

    public void clearFields() {
        usernameText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        roleText.setSelectedIndex(0);
        passwordText.setText("");
    }

    public boolean validateFields() {
        if (usernameText.getText().isEmpty() || firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty()
                || passwordText.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Fields Required",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public void register() {
        String username = usernameText.getText();
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String password = new String(passwordText.getPassword());
        String role = roleText.getSelectedItem().toString();
        if (DatabaseConnection.getUserByUsername(username) == null) {
            User user = new User(0, firstName, lastName, username, role, password);
            DatabaseConnection.createUser(user);
            System.out.println("User registered with ID: " + user.getId());
            JOptionPane.showMessageDialog(null, "User created succesfully, please log in.", "User created",
                    JOptionPane.INFORMATION_MESSAGE);
            appUI.showPanel("loginForm");
        } else {
            System.out.println("User was not registered because the username is already in use");
            JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.",
                    "Username already in used",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
