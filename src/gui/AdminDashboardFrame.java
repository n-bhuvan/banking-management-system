package gui;

import javax.swing.*;

import models.Account;
import services.BankService;

import java.awt.*;
import java.util.ArrayList;

public class AdminDashboardFrame extends JFrame implements java.awt.event.ActionListener {

    private JTextArea accountArea;

    private JScrollPane scrollPane;

    private BankService bankService;

    private JButton analyticsButton;

    public AdminDashboardFrame() {

        bankService = new BankService();

        // Frame
        setTitle("Admin Dashboard");

        setSize(600, 500);

        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Title
        JLabel titleLabel =
                new JLabel(
                        "All Bank Accounts",
                        SwingConstants.CENTER);

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24));

        add(titleLabel,
                BorderLayout.NORTH);

        analyticsButton =
                new JButton("Open Analytics");

        analyticsButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        14));

        add(analyticsButton,
                BorderLayout.SOUTH);

        analyticsButton.addActionListener(this);

        // Text Area
        accountArea =
                new JTextArea();

        accountArea.setEditable(false);

        scrollPane =
                new JScrollPane(accountArea);

        add(scrollPane,
                BorderLayout.CENTER);

        loadAccounts();

        setVisible(true);
    }

    // Load Accounts
    private void loadAccounts() {

        ArrayList<Account> accounts =
                bankService.getAllAccounts();

        StringBuilder builder =
                new StringBuilder();

        for (Account account : accounts) {

            builder.append(
                    "Account Number: ")
                    .append(account.getAccountNumber())
                    .append("\n");

            builder.append(
                    "Holder Name: ")
                    .append(account.getAccountHolderName())
                    .append("\n");

            builder.append(
                    "Balance: ")
                    .append(account.getBalance())
                    .append("\n");

            builder.append(
                    "Role: ")
                    .append(account.getRole())
                    .append("\n");

            builder.append(
                    "-----------------------------\n");
        }

        accountArea.setText(
                builder.toString());
    }

    @Override
    public void actionPerformed(
            java.awt.event.ActionEvent e) {

        new AnalyticsFrame();
    }
}