package com.plataforma_digital.gui;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JLabel;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Publication;

public class ViewAllPublications extends JPanel {
    Home home;
    JLabel viewAllPublicationsLabel;

    public ViewAllPublications(Home home) {
        this.home = home;
        viewAllPublicationsLabel = new JLabel("View All Publications");
        add(viewAllPublicationsLabel);
        List<Publication> publications = DatabaseConnection.getAllPublications();
        for (Publication publication : publications) {
            System.out.println("Title: " + publication.getTitle());
            System.out.println("Description: " + publication.getDescription());
            System.out.println("Created At: " + publication.getCreatedAt());
            add(new JLabel("Creador: " + publication.getUserId()));
            add(new JLabel("Title: " + publication.getTitle()));
            add(new JLabel("Description: " + publication.getDescription()));
            add(new JLabel("Created At: " + publication.getCreatedAt()));
        }
    }
}
