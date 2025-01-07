package com.plataforma_digital.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Publication;

public class Moderation extends JPanel {
    JLabel moderationLabel;
    Home home;
    List<JPanel> publicationPanels = new ArrayList<>();

    public Moderation(Home home) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
    }

    private void initComponents() {
        moderationLabel = new JLabel("Moderation");
        moderationLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(moderationLabel);
        getPublications();
    }

    public void getPublications() {
        List<Publication> publications = DatabaseConnection.getAllPublicationsByState("in moderation");
        for (Publication publication : publications) {
            JPanel publicationPanel = new JPanel();
            publicationPanel.add(new JLabel("Title: " + publication.getTitle()));
            publicationPanel.add(new JLabel("Created At: " + publication.getCreatedAt()));
            JButton viewPublicationButton = new JButton("View Publication");
            viewPublicationButton.addActionListener(e -> {
                ViewPublication viewPublication = new ViewPublication(home, publication);
                home.addAndShowPanel(viewPublication, "viewPublication");
            });
            JButton approvePublicationButton = new JButton("Approve Publication");
            JButton rejectPublicationButton = new JButton("Reject Publication");
            approvePublicationButton.addActionListener(e -> {
                publication.setState("approved");
                DatabaseConnection.updatePublication(publication);
                JOptionPane.showMessageDialog(null, "The publication has been approved", "Publication Approved",
                        JOptionPane.NO_OPTION);
                approvePublicationButton.setEnabled(false);
                rejectPublicationButton.setEnabled(false);
            });
            rejectPublicationButton.addActionListener(e -> {
                publication.setState("rejected");
                DatabaseConnection.updatePublication(publication);
                JOptionPane.showMessageDialog(null, "The publication has been rejected", "Publication Rejected",
                        JOptionPane.NO_OPTION);
                rejectPublicationButton.setEnabled(false);
                approvePublicationButton.setEnabled(false);

            });
            publicationPanel.add(viewPublicationButton);
            publicationPanel.add(approvePublicationButton);
            publicationPanel.add(rejectPublicationButton);
            publicationPanels.add(publicationPanel);
            add(publicationPanel);
        }
    }

    public void clearPublications() {
        for (JPanel publicationPanel : publicationPanels) {
            remove(publicationPanel);
        }
    }
}
