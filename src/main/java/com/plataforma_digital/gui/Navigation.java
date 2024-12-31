package com.plataforma_digital.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Navigation extends JPanel {
    public Home home;
    JButton editProfileButton;
    JButton calendarButton;

    public Navigation(Home home) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> this.home.showPanel("editProfile"));
        editProfileButton.setSize(165, 25);

        calendarButton = new JButton("Calendar");
        calendarButton.addActionListener(e -> this.home.showPanel("calendar"));
        calendarButton.setSize(165, 25);

        add(editProfileButton);
        add(calendarButton);
    }

}
