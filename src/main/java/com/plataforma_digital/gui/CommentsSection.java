package com.plataforma_digital.gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Comment;
import com.plataforma_digital.entities.Publication;

public class CommentsSection extends JPanel {
    Publication publication;

    public CommentsSection(Publication publication) {
        this.publication = publication;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        updateComments();
    }

    public void updateComments() {
        removeAll();

        List<Comment> comments = DatabaseConnection.getAllCommentsByPublicationId(publication.getUserId());
        for (Comment comment : comments) {
            JPanel commentPanel = new JPanel();
            commentPanel.add(
                    new JLabel("Comment by: " + DatabaseConnection.getUserById(comment.getUserId()).getUsername()));
            commentPanel.add(new JLabel(comment.getText()));
            commentPanel.add(new JLabel("Created At: " + comment.getCreatedAt()));
            add(commentPanel);
        }
        revalidate();
        repaint();
    }
}
