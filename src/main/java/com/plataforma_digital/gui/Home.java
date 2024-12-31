package com.plataforma_digital.gui;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class Home extends JPanel {
    public AppUI appUI;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Home(AppUI appUI) {
        this.appUI = appUI;
        initComponents();
    }

    private void initComponents() {
        Navigation navigation = new Navigation(this);
        add(navigation);

        EditProfile editProfile = new EditProfile(this);
        Calendar calendar = new Calendar(this);
        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(editProfile, "editProfile");
        mainPanel.add(calendar, "calendar");
        add(mainPanel);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
