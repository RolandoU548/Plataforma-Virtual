package com.plataforma_digital.gui;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class Home extends JPanel {
    public AppUI appUI;
    private CardLayout cardLayout;
    public JPanel mainPanel;
    public Navigation navigation;
    public EditProfile editProfile;
    public CreatePublication createPublication;
    public ViewAllPublications viewAllPublications;
    public Calendar calendar;
    public Moderation moderation;

    public Home(AppUI appUI) {
        this.appUI = appUI;
        initComponents();
    }

    private void initComponents() {
        navigation = new Navigation(this);
        add(navigation);

        editProfile = new EditProfile(this);
        createPublication = new CreatePublication(this);
        viewAllPublications = new ViewAllPublications(this);
        calendar = new Calendar(this);
        moderation = new Moderation(this);

        cardLayout = new CardLayout();
        mainPanel = new JPanel();
        mainPanel.setLayout(cardLayout);

        mainPanel.add(editProfile, "editProfile");
        mainPanel.add(createPublication, "createPublication");
        mainPanel.add(calendar, "calendar");
        mainPanel.add(moderation, "moderation");
        addAndShowPanel(viewAllPublications, "viewAllPublications");
        add(mainPanel);

    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public void addAndShowPanel(JPanel panel, String name) {
        mainPanel.remove(panel);
        mainPanel.add(panel, name);
        mainPanel.revalidate();
        cardLayout.show(mainPanel, name);
    }
}
