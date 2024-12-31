package com.plataforma_digital.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.plataforma_digital.entities.CurrentUser;

public class EditProfile extends JPanel {
    public Home home;
    JLabel editProfileLabel;
    private JLabel usernameLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel roleLabel;
    private JLabel passwordLabel;

    public EditProfile(Home home) {
        this.home = home;
        editProfileLabel = new JLabel("Edit Profile");
        editProfileLabel.setSize(80, 25);

        usernameLabel = new JLabel("Username: " + CurrentUser.getUsername());
        usernameLabel.setSize(80, 25);

        firstNameLabel = new JLabel("FirstName: " + CurrentUser.getFirstName());
        firstNameLabel.setSize(80, 25);

        lastNameLabel = new JLabel("Lastname: " + CurrentUser.getLastName());
        lastNameLabel.setSize(80, 25);

        roleLabel = new JLabel("Role: " + CurrentUser.getRole());
        roleLabel.setSize(80, 25);

        passwordLabel = new JLabel("Password: " + CurrentUser.getPassword());
        passwordLabel.setSize(80, 25);

        add(editProfileLabel);
        add(usernameLabel);
        add(firstNameLabel);
        add(lastNameLabel);
        add(roleLabel);
        add(passwordLabel);
    }

    public void updateProfileInfo() {
        usernameLabel.setText("Username: " + CurrentUser.getUsername());
        firstNameLabel.setText("FirstName: " + CurrentUser.getFirstName());
        lastNameLabel.setText("Lastname: " + CurrentUser.getLastName());
        roleLabel.setText("Role: " + CurrentUser.getRole());
        passwordLabel.setText("Password: " + CurrentUser.getPassword());
    }
}
