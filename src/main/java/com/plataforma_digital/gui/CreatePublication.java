package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.FlowLayout;

import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.CurrentUser;
import com.plataforma_digital.entities.Publication;

public class CreatePublication extends JPanel {
    public Home home;
    JLabel createPublicationLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JTextField titleTextField;
    private JTextField descriptionTextField;
    private JPanel panel1;
    private JPanel panel2;
    private JButton createPublicationButton;

    public CreatePublication(Home home) {
        this.home = home;

        createPublicationLabel = new JLabel("Create Publication");
        createPublicationLabel.setSize(80, 25);

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        titleLabel = new JLabel("Title");
        titleLabel.setSize(80, 25);
        titleTextField = new JTextField(20);
        titleTextField.setSize(165, 25);
        panel1.add(titleLabel);
        panel1.add(titleTextField);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setSize(80, 25);
        descriptionTextField = new JTextField(20);
        descriptionTextField.setSize(165, 25);
        panel2.add(descriptionLabel);
        panel2.add(descriptionTextField);

        createPublicationButton = new JButton("Create Publication");
        createPublicationButton.setSize(80, 25);

        createPublicationButton.addActionListener(e -> {
            if (validateFields()) {
                createPublication();
                clearFields();
            }
        });

        add(createPublicationLabel);
        add(panel1);
        add(panel2);
        add(createPublicationButton);
    }

    private void clearFields() {
        titleTextField.setText("");
        descriptionTextField.setText("");
    }

    private boolean validateFields() {
        if (titleTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required", "Fields Required",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    private void createPublication() {
        Publication newPublication = new Publication(0, CurrentUser.getId(), titleTextField.getText(),
                descriptionTextField.getText(), null);
        DatabaseConnection.createPublication(newPublication);
    }
}
