package gui;

import javax.swing.*;

import services.BankService;

import java.awt.*;

public class AnalyticsFrame extends JFrame {

    private JLabel totalAccountsLabel;
    private JLabel totalBalanceLabel;
    private JLabel totalUsersLabel;
    private JLabel totalAdminsLabel;

    private BankService bankService;

    public AnalyticsFrame() {

        bankService = new BankService();

        // Frame
        setTitle("Bank Analytics Dashboard");

        setSize(500, 350);

        setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        // Title
        JLabel title =
                new JLabel("Bank Analytics");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24));

        title.setBounds(
                140,
                20,
                250,
                30);

        add(title);

        // Total Accounts
        totalAccountsLabel =
                new JLabel(
                        "Total Accounts: "
                                + bankService
                                .getAllAccounts()
                                .size());

        totalAccountsLabel.setBounds(
                80,
                90,
                300,
                30);

        totalAccountsLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        18));

        add(totalAccountsLabel);

        // Total Balance
        totalBalanceLabel =
                new JLabel(
                        "Total Bank Balance: "
                                + bankService
                                .getTotalBankBalance());

        totalBalanceLabel.setBounds(
                80,
                140,
                350,
                30);

        totalBalanceLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        18));

        add(totalBalanceLabel);

        // Total Users
        totalUsersLabel =
                new JLabel(
                        "Total Users: "
                                + bankService
                                .getTotalUsers());

        totalUsersLabel.setBounds(
                80,
                190,
                300,
                30);

        totalUsersLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        18));

        add(totalUsersLabel);

        // Total Admins
        totalAdminsLabel =
                new JLabel(
                        "Total Admins: "
                                + bankService
                                .getTotalAdmins());

        totalAdminsLabel.setBounds(
                80,
                240,
                300,
                30);

        totalAdminsLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        18));

        add(totalAdminsLabel);

        setVisible(true);
    }
}