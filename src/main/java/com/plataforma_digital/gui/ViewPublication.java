package com.plataforma_digital.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Publication;
import com.plataforma_digital.entities.User;

public class ViewPublication extends JPanel {
    Home home;
    JPanel publicationPanel;
    JLabel viewPublicationLabel;
    JLabel publicationCreatorLabel;
    JLabel publicationTitleLabel;
    JLabel publicationDescriptionLabel;
    JLabel publicationCreatedAtLabel;
    JTextArea commentTextArea;
    JLabel commentLabel;
    JButton commentButton;
    CreateComment createComment;
    CommentsSection commentsSection;

    public ViewPublication(Home home, Publication publication) {
        this.home = home;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents(publication);
    }

    private void initComponents(Publication publication) {
        initPublicationPanel(publication);
        initCommentsSection(publication);
        initCreateComment(publication);

        add(createComment);
        add(commentsSection);
    }

    private void initPublicationPanel(Publication publication) {
        User creator = DatabaseConnection.getUserById(publication.getUserId());
        publicationPanel = new JPanel();
        viewPublicationLabel = new JLabel("View Publication");
        publicationCreatorLabel = new JLabel(
                "Created by: " + creator.getUsername());
        publicationTitleLabel = new JLabel("Title: " + publication.getTitle());
        publicationDescriptionLabel = new JLabel("Description: " + publication.getDescription());
        publicationCreatedAtLabel = new JLabel("Created at: " + publication.getCreatedAt());

        JPanel helperPanel = new JPanel();
        helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.Y_AXIS));
        helperPanel.add(viewPublicationLabel);
        helperPanel.add(publicationCreatorLabel);
        helperPanel.add(publicationTitleLabel);
        helperPanel.add(publicationDescriptionLabel);
        helperPanel.add(publicationCreatedAtLabel);
        publicationPanel.add(helperPanel);
        add(publicationPanel);
    }

    private void initCommentsSection(Publication publication) {
        commentsSection = new CommentsSection(publication.getId());
        commentsSection.updateComments(publication.getId());
    }

    private void initCreateComment(Publication publication) {
        createComment = new CreateComment(commentsSection, publication);
    }
}
