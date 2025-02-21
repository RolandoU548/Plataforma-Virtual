package com.plataforma_digital.gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Color;
import com.plataforma_digital.database.DatabaseConnection;
import com.plataforma_digital.entities.Publication;

public class PublicationsCalendar extends JPanel {
    public Home home;
    private JLabel monthLabel;
    private JPanel daysPanel;
    private Calendar calendar;
    private List<Date> highlightedDates;

    public PublicationsCalendar(Home home) {
        this.home = home;
        highlightedDates = new ArrayList<>();
        List<Publication> publications = DatabaseConnection.getAllPublicationsByState("approved");
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (Publication publication : publications) {
                Date date = originalFormat.parse(publication.getCreatedAt());
                String formattedDate = targetFormat.format(date);
                highlightedDates.add(targetFormat.parse(formattedDate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void updateCalendar() {
        daysPanel.removeAll();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        String[] days = { "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" };
        for (String day : days) {
            daysPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            Date currentDate = calendar.getTime();
            JLabel dayLabel = new JLabel(String.valueOf(day), SwingConstants.CENTER);

            if (highlightedDates.stream().anyMatch(date -> sdf.format(date).equals(sdf.format(currentDate)))) {
                dayLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            daysPanel.add(dayLabel);
        }

        monthLabel.setText(new SimpleDateFormat("MMMM yyyy").format(calendar.getTime()));
        daysPanel.revalidate();
        daysPanel.repaint();
    }

    private void initComponents() {
        calendar = Calendar.getInstance();
        monthLabel = new JLabel();
        daysPanel = new JPanel(new GridLayout(0, 7));

        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        prevButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        nextButton.addActionListener(e -> {
            calendar.add(Calendar.MONTH, 1);
            updateCalendar();
        });

        JPanel topPanel = new JPanel();
        topPanel.add(prevButton);
        topPanel.add(monthLabel);
        topPanel.add(nextButton);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(daysPanel, BorderLayout.CENTER);
        updateCalendar();
    }
}
