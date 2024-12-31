package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditProfile extends JPanel {
    public Home home;
    JLabel welcomeLabel;
    JButton logoutButton;

    public EditProfile(Home home) {
        this.home = home;
        welcomeLabel = new JLabel("Welcome to the platform");
        welcomeLabel.setSize(80, 25);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> this.home.appUI.showPanel("loginForm"));
        logoutButton.setSize(165, 25);
        add(welcomeLabel);
        add(logoutButton);
    }
}
