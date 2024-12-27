package com.plataforma_digital.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JTextField userField;
    private JTextField passField;

    public LoginForm(AppUI appUI) {

        JLabel userLabel = new JLabel("Usuario:");
        userField = new JTextField(20);

        JLabel passLabel = new JLabel("Contraseña:");
        passField = new JTextField(20);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appUI.showPanel("registerForm");
            }
        });

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginButton);
    }
}
