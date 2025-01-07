package com.plataforma_digital.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.plataforma_digital.entities.CurrentUser;

public class Navigation extends JPanel {
    public Home home;
    JButton editProfileButton;
    JButton createPublicationButton;
    JButton viewAllPublicationsButton;
    JButton calendarButton;
    JButton moderationButton;
    JButton logoutButton;

    public Navigation(Home home) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> {
            EditProfile editProfile = new EditProfile(home);
            home.editProfile = editProfile;
            home.addAndShowPanel(editProfile, "editProfile");
        });
        editProfileButton.setSize(165, 25);

        createPublicationButton = new JButton("Create Publication");
        createPublicationButton.addActionListener(e -> home.showPanel("createPublication"));
        createPublicationButton.setSize(165, 25);

        viewAllPublicationsButton = new JButton("View All Publications");
        viewAllPublicationsButton.addActionListener(e -> {
            ViewAllPublications viewAllPublications = new ViewAllPublications(home);
            home.viewAllPublications = viewAllPublications;
            home.addAndShowPanel(viewAllPublications, "viewAllPublications");
        });
        viewAllPublicationsButton.setSize(165, 25);

        calendarButton = new JButton("Calendar");
        calendarButton.addActionListener(e -> {
            Calendar calendar = new Calendar(home);
            home.calendar = calendar;
            home.addAndShowPanel(calendar, "calendar");
        });
        calendarButton.setSize(165, 25);

        moderationButton = new JButton("Moderation");
        moderationButton.addActionListener(e -> {
            Moderation moderation = new Moderation(home);
            home.moderation = moderation;
            home.addAndShowPanel(moderation, "moderation");
        });
        moderationButton.setSize(165, 25);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            home.showPanel("viewAllPublications");
            home.appUI.showPanel("loginForm");
            CurrentUser.clear();
        });
        logoutButton.setSize(165, 25);

        add(editProfileButton);
        add(createPublicationButton);
        add(viewAllPublicationsButton);
        add(calendarButton);
        add(moderationButton);
        add(logoutButton);
    }
}
