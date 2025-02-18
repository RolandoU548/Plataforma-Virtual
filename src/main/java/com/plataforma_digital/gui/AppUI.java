package com.plataforma_digital.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.plataforma_digital.database.DatabaseConnection;

public class AppUI extends JFrame {
    public RegisterForm registerForm;
    public LoginForm loginForm;
    public Home home;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AppUI() {
        setTitle("Plataforma Digital");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                DatabaseConnection.disconnect();
            }
        });
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        registerForm = new RegisterForm(this);
        loginForm = new LoginForm(this);
        home = new Home(this);

        mainPanel.add(loginForm, "loginForm");
        mainPanel.add(registerForm, "registerForm");
        mainPanel.add(home, "home");

        add(mainPanel);

        cardLayout.show(mainPanel,
                "loginForm");
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }
}
