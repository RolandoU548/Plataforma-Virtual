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
    JButton logoutButton;

    public Navigation(Home home) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> {
            home.editProfile = new EditProfile(home);
            home.mainPanel.add(home.editProfile, "editProfile");
            home.showPanel("editProfile");
        });
        editProfileButton.setSize(165, 25);

        createPublicationButton = new JButton("Create Publication");
        createPublicationButton.addActionListener(e -> home.showPanel("createPublication"));
        createPublicationButton.setSize(165, 25);

        viewAllPublicationsButton = new JButton("View All Publications");
        viewAllPublicationsButton.addActionListener(e -> {
            home.viewAllPublications = new ViewAllPublications(home);
            home.mainPanel.add(home.viewAllPublications, "viewAllPublications");
            home.showPanel("viewAllPublications");
        });
        viewAllPublicationsButton.setSize(165, 25);

        calendarButton = new JButton("Calendar");
        calendarButton.addActionListener(e -> {
            home.calendar = new Calendar(home);
            home.mainPanel.add(home.calendar, "calendar");
            home.showPanel("calendar");
        });
        calendarButton.setSize(165, 25);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            home.appUI.showPanel("loginForm");
            CurrentUser.clear();
        });
        logoutButton.setSize(165, 25);

        add(editProfileButton);
        add(createPublicationButton);
        add(viewAllPublicationsButton);
        add(calendarButton);
        add(logoutButton);
    }

}
