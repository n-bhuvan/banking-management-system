package gui;

import javax.swing.*;

import java.awt.*;
import java.sql.*;

import utils.DatabaseConnection;

public class AnalyticsFrame
        extends JFrame {

    // Colors
    private final Color BACKGROUND_COLOR =
            new Color(24, 26, 27);

    private final Color CARD_COLOR =
            new Color(40, 44, 52);

    private final Color TEXT_COLOR =
            Color.WHITE;

    private final Color ACCENT_COLOR =
            new Color(0, 120, 215);

    public AnalyticsFrame() {

        setTitle("Bank Analytics");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(
                BACKGROUND_COLOR);

        setLayout(new BorderLayout());

        // Title
        JLabel title =
                new JLabel(
                        "Bank Analytics Dashboard",
                        SwingConstants.CENTER);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        32));

        title.setForeground(
                TEXT_COLOR);

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        0,
                        25,
                        0));

        add(title, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel =
                new JPanel();

        mainPanel.setBackground(
                BACKGROUND_COLOR);

        mainPanel.setLayout(
                new GridLayout(2,2,25,25));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20));

        // Analytics Data
        int totalAccounts =
                getTotalAccounts();

        int totalUsers =
                getTotalUsers();

        int totalAdmins =
                getTotalAdmins();

        double totalBalance =
                getTotalBalance();

        // Cards
        mainPanel.add(
                createCard(
                        "Total Accounts",
                        String.valueOf(
                                totalAccounts)));

        mainPanel.add(
                createCard(
                        "Total Users",
                        String.valueOf(
                                totalUsers)));

        mainPanel.add(
                createCard(
                        "Total Admins",
                        String.valueOf(
                                totalAdmins)));

        mainPanel.add(
                createCard(
                        "Total Balance",
                        "₹" + totalBalance));

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Create Analytics Card
    private JPanel createCard(
            String title,
            String value) {

        JPanel card =
                new JPanel();

        card.setBackground(
                CARD_COLOR);

        card.setLayout(
                new BorderLayout());

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                ACCENT_COLOR,
                                2),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        JLabel titleLabel =
                new JLabel(
                        title,
                        SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        titleLabel.setForeground(
                TEXT_COLOR);

        JLabel valueLabel =
                new JLabel(
                        value,
                        SwingConstants.CENTER);

        valueLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        36));

        valueLabel.setForeground(
                Color.WHITE);

        card.add(
                titleLabel,
                BorderLayout.NORTH);

        card.add(
                valueLabel,
                BorderLayout.CENTER);

        return card;
    }

    // Total Accounts
    private int getTotalAccounts() {

        String query =
                "SELECT COUNT(*) FROM accounts";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // Total Users
    private int getTotalUsers() {

        String query =
                "SELECT COUNT(*) FROM accounts "
                + "WHERE role = 'user'";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // Total Admins
    private int getTotalAdmins() {

        String query =
                "SELECT COUNT(*) FROM admins";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    // Total Balance
    private double getTotalBalance() {

        String query =
                "SELECT SUM(balance) FROM accounts";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return resultSet.getDouble(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}