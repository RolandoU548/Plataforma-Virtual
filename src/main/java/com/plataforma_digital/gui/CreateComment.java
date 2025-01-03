package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Comment;
import com.plataforma_digital.entities.CurrentUser;
import com.plataforma_digital.entities.Publication;

public class CreateComment extends JPanel {
    JTextArea commentTextArea;
    JLabel commentLabel;
    JButton commentButton;

    public CreateComment(CommentsSection commentsSection, Publication publication) {
        commentLabel = new JLabel("Write a comment:");
        commentTextArea = new JTextArea(1, 20);
        add(commentTextArea);
        commentButton = new JButton("Comment");
        commentButton.addActionListener(e -> {
            DatabaseConnection
                    .createComment(
                            new Comment(0, CurrentUser.getId(), publication.getId(), commentTextArea.getText(), null));
            commentsSection.updateComments(publication.getId());
        });
        add(commentButton);
    }
}
