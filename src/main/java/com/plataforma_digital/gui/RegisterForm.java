package com.plataforma_digital.gui;

import com.plataforma_digital.database.DatabaseConnection;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterForm extends JPanel implements ActionListener {
    private DatabaseConnection db;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel roleLabel;
    private JLabel passwordLabel;
    private JTextField firstNameText;
    private JTextField lastNameText;
    private JComboBox<String> roleText;
    private JPasswordField passwordText;
    private JButton loginButton;

    public RegisterForm(DatabaseConnection db) {
        this.db = db;

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setSize(80, 25);
        panel1.add(firstNameLabel);
        firstNameText = new JTextField(20);
        firstNameText.setSize(165, 25);
        panel1.add(firstNameText);

        lastNameLabel = new JLabel("LastName");
        lastNameLabel.setSize(80, 25);
        panel2.add(lastNameLabel);
        lastNameText = new JTextField(20);
        lastNameText.setSize(165, 25);
        panel2.add(lastNameText);

        roleLabel = new JLabel("Role");
        roleLabel.setSize(80, 25);
        panel3.add(roleLabel);
        roleText = new JComboBox<String>();
        roleText.addItem("Student");
        roleText.addItem("Professor");
        roleText.addItem("Support Personal");
        panel3.add(roleText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(80, 25);
        panel4.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setSize(165, 25);
        panel4.add(passwordText);

        loginButton = new JButton("Register");
        loginButton.setSize(80, 25);
        loginButton.addActionListener(this);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(loginButton);
    }

    public void clearFields() {
        firstNameText.setText("");
        lastNameText.setText("");
        roleText.setSelectedIndex(0);
        passwordText.setText("");
    }

    public void register() {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String password = passwordText.getPassword().toString();
        String role = roleText.getSelectedItem().toString();
        String sql = "INSERT INTO users(first_name, last_name, role, password) VALUES(?, ?, ?, ?)";
        db.executePreparedStatement(sql, firstName, lastName, role, password);
    }

    public void actionPerformed(ActionEvent e) {
        register();
        clearFields();
    }
}
