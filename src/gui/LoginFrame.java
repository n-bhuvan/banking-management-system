package gui;

import javax.swing.*;

import models.Account;
import services.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame
        implements ActionListener {

    JLabel titleLabel;

    JLabel accountLabel;
    JLabel passwordLabel;

    JTextField accountField;
    JPasswordField passwordField;

    JButton loginButton;

    BankService bankService;

    public LoginFrame() {

        bankService = new BankService();

        // Frame
        setTitle("Smart Banking System");

        setSize(400, 300);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Title
        titleLabel =
                new JLabel("Bank Login");

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        titleLabel.setBounds(
                130,
                20,
                200,
                30);

        add(titleLabel);

        // Account Number
        accountLabel =
                new JLabel("Account Number:");

        accountLabel.setBounds(
                50,
                80,
                120,
                25);

        add(accountLabel);

        accountField =
                new JTextField();

        accountField.setBounds(
                180,
                80,
                150,
                25);

        add(accountField);

        // Password
        passwordLabel =
                new JLabel("Password:");

        passwordLabel.setBounds(
                50,
                130,
                120,
                25);

        add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                180,
                130,
                150,
                25);

        add(passwordField);

        // Login Button
        loginButton =
                new JButton("Login");

        loginButton.setBounds(
                140,
                190,
                100,
                30);

        loginButton.addActionListener(this);

        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int accountNumber =
                Integer.parseInt(
                        accountField.getText());

        String password =
                String.valueOf(
                        passwordField.getPassword());

        boolean loginSuccess =
                bankService.login(
                        accountNumber,
                        password);

        if (loginSuccess) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!");

            Account account =
                    bankService.findAccount(accountNumber);

            new DashboardFrame(account);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Credentials!");
        }
    }
}