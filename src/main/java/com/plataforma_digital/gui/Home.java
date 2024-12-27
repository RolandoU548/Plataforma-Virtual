package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel {
    private AppUI appUI;
    JLabel welcomeLabel;
    JButton logoutButton;

    public Home(AppUI appUI) {
        this.appUI = appUI;
        welcomeLabel = new JLabel("Welcome to the platform");
        add(welcomeLabel);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> appUI.showPanel("loginForm"));
        add(logoutButton);
    }
}
