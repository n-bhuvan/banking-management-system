package gui;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.sql.*;

import utils.DatabaseConnection;

public class AdminDashboardFrame
        extends JFrame {

    private JTable accountTable;

    private DefaultTableModel model;

    // Colors
    private final Color BACKGROUND_COLOR =
            new Color(24, 26, 27);

    private final Color CARD_COLOR =
            new Color(40, 44, 52);

    private final Color TEXT_COLOR =
            Color.WHITE;

    private final Color BUTTON_COLOR =
            new Color(0, 120, 215);

    public AdminDashboardFrame() {

        setTitle("Admin Dashboard");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(
                BACKGROUND_COLOR);

        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel =
                new JPanel();

        topPanel.setBackground(
                CARD_COLOR);

        topPanel.setPreferredSize(
                new Dimension(1000, 80));

        JLabel title =
                new JLabel(
                        "Bank Administration Panel");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        title.setForeground(
                TEXT_COLOR);

        topPanel.add(title);

        add(topPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {

                "Account Number",
                "Holder Name",
                "Balance",
                "Role"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0);

        accountTable =
                new JTable(model);

        accountTable.setRowHeight(30);

        accountTable.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        14));

        accountTable.getTableHeader()
                .setFont(
                        new Font("Arial",
                                Font.BOLD,
                                15));

        accountTable.setBackground(
                CARD_COLOR);

        accountTable.setForeground(
                TEXT_COLOR);

        accountTable.getTableHeader()
                .setBackground(
                        BUTTON_COLOR);

        accountTable.getTableHeader()
                .setForeground(
                        Color.WHITE);

        JScrollPane scrollPane =
                new JScrollPane(accountTable);

                JPanel centerPanel =
                new JPanel(
                        new BorderLayout());

        centerPanel.setBackground(
                BACKGROUND_COLOR);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20));

        JPanel tableCard =
                new JPanel(
                        new BorderLayout());

        tableCard.setBackground(
                CARD_COLOR);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(70,70,70),
                                1),

                        BorderFactory.createEmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER);

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER);

        add(centerPanel,
                BorderLayout.CENTER);
        // Bottom Panel
        JPanel bottomPanel =
                new JPanel();
        JButton analyticsButton =
                new JButton("Analytics");

        analyticsButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        analyticsButton.setBackground(
                new Color(40,167,69));

        analyticsButton.setForeground(
                Color.WHITE);

        analyticsButton.setFocusPainted(false);

        analyticsButton.addActionListener(
                e -> new AnalyticsFrame());

        bottomPanel.add(analyticsButton);

        bottomPanel.setBackground(
                BACKGROUND_COLOR);

        JButton refreshButton =
                new JButton("Refresh");

        refreshButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        refreshButton.setBackground(
                BUTTON_COLOR);

        refreshButton.setForeground(
                Color.WHITE);

        refreshButton.setFocusPainted(false);

        refreshButton.addActionListener(
                e -> loadAccounts());

        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load Data
        loadAccounts();

        setVisible(true);
    }

    private void loadAccounts() {

        model.setRowCount(0);

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            String query =
                    "SELECT * FROM accounts";

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                Object[] row = {

                        resultSet.getLong(
                                "account_number"),

                        resultSet.getString(
                                "account_holder_name"),

                        resultSet.getDouble(
                                "balance"),

                        resultSet.getString(
                                "role")
                };

                model.addRow(row);
            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}