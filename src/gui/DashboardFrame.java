package gui;

import javax.swing.*;

import models.Account;
import services.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame
        implements ActionListener {

    private Account currentAccount;

    private JLabel welcomeLabel;

    private JButton balanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton transactionButton;

    private BankService bankService;

    public DashboardFrame(Account account) {

        currentAccount = account;

        bankService = new BankService();

        // Frame
        setTitle("Bank Dashboard");

        setSize(500, 400);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Welcome Label
        welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + currentAccount.getAccountHolderName());

        welcomeLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        20));

        welcomeLabel.setBounds(
                120,
                30,
                300,
                30);

        add(welcomeLabel);

        // Balance Button
        balanceButton =
                new JButton("Check Balance");

        balanceButton.setBounds(
                150,
                90,
                180,
                35);

        balanceButton.addActionListener(this);

        add(balanceButton);

        // Deposit Button
        depositButton =
                new JButton("Deposit");

        depositButton.setBounds(
                150,
                140,
                180,
                35);

        depositButton.addActionListener(this);

        add(depositButton);

        // Withdraw Button
        withdrawButton =
                new JButton("Withdraw");

        withdrawButton.setBounds(
                150,
                190,
                180,
                35);

        withdrawButton.addActionListener(this);

        add(withdrawButton);

        // Transfer Button
        transferButton =
                new JButton("Transfer Money");

        transferButton.setBounds(
                150,
                240,
                180,
                35);

        transferButton.addActionListener(this);

        add(transferButton);

        // Transaction Button
        transactionButton =
                new JButton("Transactions");

        transactionButton.setBounds(
                150,
                290,
                180,
                35);

        transactionButton.addActionListener(this);

        add(transactionButton);

        setVisible(true);
    }

    private void refreshAccountData() {

        currentAccount =
                bankService.findAccount(
                        currentAccount
                                .getAccountNumber());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Check Balance
        if (e.getSource() == balanceButton) {

            JOptionPane.showMessageDialog(
                    this,
                    "Current Balance: "
                            + currentAccount.getBalance());
        }

        // Deposit
        else if (e.getSource() == depositButton) {

            String amountText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Deposit Amount:");

            double amount =
                    Double.parseDouble(amountText);

            bankService.depositMoney(
                    currentAccount.getAccountNumber(),
                    amount);
            refreshAccountData();

            JOptionPane.showMessageDialog(
                    this,
                    "Deposit Successful!");
        }

        // Withdraw
        else if (e.getSource() == withdrawButton) {

            String amountText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Withdrawal Amount:");

            double amount =
                    Double.parseDouble(amountText);

            bankService.withdrawMoney(
                    currentAccount.getAccountNumber(),
                    amount);
            refreshAccountData();
            JOptionPane.showMessageDialog(
                    this,
                    "Withdrawal Successful!");
        }

        // Transfer
        else if (e.getSource() == transferButton) {

            String receiverText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Receiver Account Number:");

            int receiverAccount =
                    Integer.parseInt(receiverText);

            String amountText =
                    JOptionPane.showInputDialog(
                            this,
                            "Enter Transfer Amount:");

            double amount =
                    Double.parseDouble(amountText);

            bankService.transferMoney(
                    currentAccount.getAccountNumber(),
                    receiverAccount,
                    amount);
            refreshAccountData();

            JOptionPane.showMessageDialog(
                    this,
                    "Transfer Successful!");
        }

        // Transactions
        else if (e.getSource() == transactionButton) {

            currentAccount.showTransactionHistory();
        }
    }
}