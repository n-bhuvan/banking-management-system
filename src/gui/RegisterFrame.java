package gui;

import javax.swing.*;

import services.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame
        implements ActionListener {

    private JTextField accountField;
    private JTextField nameField;
    private JTextField balanceField;
    private JTextField roleField;

    private JPasswordField passwordField;

    private JButton registerButton;

    private BankService bankService;

    public RegisterFrame() {

        bankService = new BankService();

        // Frame
        setTitle("Create Account");

        setSize(400, 450);

        setLayout(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title =
                new JLabel("Register Account");

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        title.setBounds(
                100,
                20,
                250,
                30);

        add(title);

        // Account Number
        JLabel accountLabel =
                new JLabel("Account Number:");

        accountLabel.setBounds(
                40,
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

        // Name
        JLabel nameLabel =
                new JLabel("Holder Name:");

        nameLabel.setBounds(
                40,
                130,
                120,
                25);

        add(nameLabel);

        nameField =
                new JTextField();

        nameField.setBounds(
                180,
                130,
                150,
                25);

        add(nameField);

        // Balance
        JLabel balanceLabel =
                new JLabel("Initial Balance:");

        balanceLabel.setBounds(
                40,
                180,
                120,
                25);

        add(balanceLabel);

        balanceField =
                new JTextField();

        balanceField.setBounds(
                180,
                180,
                150,
                25);

        add(balanceField);

        // Password
        JLabel passwordLabel =
                new JLabel("Password:");

        passwordLabel.setBounds(
                40,
                230,
                120,
                25);

        add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                180,
                230,
                150,
                25);

        add(passwordField);

        // Role
        JLabel roleLabel =
                new JLabel("Role:");

        roleLabel.setBounds(
                40,
                280,
                120,
                25);

        add(roleLabel);

        roleField =
                new JTextField();

        roleField.setBounds(
                180,
                280,
                150,
                25);

        add(roleField);

        // Button
        registerButton =
                new JButton("Create Account");

        registerButton.setBounds(
                110,
                340,
                160,
                35);

        registerButton.addActionListener(this);

        add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int accountNumber =
                Integer.parseInt(
                        accountField.getText());

        String name =
                nameField.getText();

        double balance =
                Double.parseDouble(
                        balanceField.getText());

        String password =
                String.valueOf(
                        passwordField.getPassword());

        String role =
                roleField.getText();

        bankService.createAccount(
                accountNumber,
                name,
                balance,
                password,
                role);

        JOptionPane.showMessageDialog(
                this,
                "Account Created Successfully!");
    }
}