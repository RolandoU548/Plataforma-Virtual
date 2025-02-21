package com.plataforma_digital.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Publication;

public class ViewAllPublications extends JPanel {
    Home home;
    JLabel viewAllPublicationsLabel;
    List<JPanel> publicationPanels = new ArrayList<>();

    public ViewAllPublications(Home home) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        viewAllPublicationsLabel = new JLabel("View All Publications");
        viewAllPublicationsLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(viewAllPublicationsLabel);
        getPublications();
    }

    public void getPublications() {
        List<Publication> publications = DatabaseConnection.getAllPublicationsByState("approved");
        for (Publication publication : publications) {
            JPanel publicationPanel = new JPanel();
            publicationPanel.add(new JLabel("Title: " + publication.getTitle()));
            publicationPanel.add(new JLabel("Created At: " + publication.getCreatedAt()));
            JButton viewPublicationButton = new JButton("View Publication");
            JButton deletePublicationButton = new JButton("Delete Publication");
            viewPublicationButton.addActionListener(e -> {
                ViewPublication viewPublication = new ViewPublication(home, publication);
                home.addAndShowPanel(viewPublication, "viewPublication");
            });
            deletePublicationButton.addActionListener(e -> {
                DatabaseConnection.deletePublicationById(publication.getId());
                clearPublications();
                getPublications();
                revalidate();
                repaint();
            });
            publicationPanel.add(viewPublicationButton);
            publicationPanel.add(deletePublicationButton);
            add(publicationPanel);
            publicationPanels.add(publicationPanel);
        }
    }

    public void clearPublications() {
        for (JPanel publicationPanel : publicationPanels) {
            remove(publicationPanel);
        }
    }
}
