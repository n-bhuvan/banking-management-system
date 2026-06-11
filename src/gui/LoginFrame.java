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

    JButton registerButton;

    BankService bankService;

    public LoginFrame() {

        bankService = new BankService();

        // Frame
        setTitle("Smart Banking System");

        setSize(500, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Main Panel
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(7, 1, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        30,
                        50));

        // Title
        JLabel title =
                new JLabel(
                        "Smart Banking Login",
                        SwingConstants.CENTER);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        24));

        panel.add(title);

        // Account Number
        accountField = new JTextField();

        panel.add(
                createFieldPanel(
                        "Account Number",
                        accountField));

        // Password
        passwordField = new JPasswordField();

        panel.add(
                createFieldPanel(
                        "Password",
                        passwordField));

        // Login Button
        loginButton =
                new JButton("Login");

        loginButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        loginButton.addActionListener(this);

        panel.add(loginButton);

        // Register Button
        registerButton =
                new JButton("Register");

        registerButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        registerButton.addActionListener(this);

        panel.add(registerButton);

        add(panel);

        setVisible(true);
    }
    
    private JPanel createFieldPanel(
        String labelText,
        JTextField field) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(10, 10));

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font("Arial",
                        Font.BOLD,
                        14));

        field.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        14));

        panel.add(label, BorderLayout.WEST);

        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    @Override
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerButton) {

            new RegisterFrame();

            return;
        }

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

            if (account.getRole()
                    .equalsIgnoreCase("admin")) {

                new AdminDashboardFrame();

            } else {

                new DashboardFrame(account);
            }

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Credentials!");
        }
    }
}