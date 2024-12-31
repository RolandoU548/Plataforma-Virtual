package com.plataforma_digital.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calendar extends JPanel {
    public Home home;
    JLabel calendarLabel;

    public Calendar(Home home) {
        this.home = home;
        calendarLabel = new JLabel("Calendar");
        calendarLabel.setSize(100, 100);
        add(calendarLabel);
    }

}
