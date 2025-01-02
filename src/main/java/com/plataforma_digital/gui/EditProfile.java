package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.FlowLayout;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.CurrentUser;
import com.plataforma_digital.entities.User;

public class EditProfile extends JPanel {
    public Home home;
    JLabel editProfileLabel;
    private JTextField usernameTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JComboBox<String> roleTextField;
    private JTextField passwordTextField;
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
    private JButton updateProfileButton;

    public EditProfile(Home home) {
        this.home = home;

        editProfileLabel = new JLabel("Edit Profile");
        editProfileLabel.setSize(80, 25);

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
        passwordTextField = new JTextField(20);
        passwordTextField.setSize(165, 25);
        panel5.add(passwordLabel);
        panel5.add(passwordTextField);

        updateProfileButton = new JButton("Update");
        updateProfileButton.setSize(80, 25);

        updateProfileButton.addActionListener(e -> {
            if (validateFields()) {
                updateProfileInfo();
            }
        });

        add(editProfileLabel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        add(panel5);
        add(updateProfileButton);
    }

    private boolean validateFields() {
        if (usernameTextField.getText().equals(CurrentUser.getUsername())
                && firstNameTextField.getText().equals(CurrentUser.getFirstName())
                && lastNameTextField.getText().equals(CurrentUser.getLastName())
                && roleTextField.getSelectedItem().toString().equals(CurrentUser.getRole())
                && passwordTextField.getText().equals(CurrentUser.getPassword())) {
            JOptionPane.showMessageDialog(null, "Change some part of your profile", "Changes Required",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public void updateCurrentUserInfo() {
        usernameTextField.setText(CurrentUser.getUsername());
        firstNameTextField.setText(CurrentUser.getFirstName());
        lastNameTextField.setText(CurrentUser.getLastName());
        roleTextField.setSelectedItem(CurrentUser.getRole());
        passwordTextField.setText(CurrentUser.getPassword());
    }

    private void updateProfileInfo() {
        User updatedUser = new User(CurrentUser.getId(), usernameTextField.getText(), firstNameTextField.getText(),
                lastNameTextField.getText(), roleTextField.getSelectedItem().toString(), passwordTextField.getText());
        DatabaseConnection
                .updateUser(updatedUser);
        CurrentUser.setUser(updatedUser);
    }
}
