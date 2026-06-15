package gui;

import javax.swing.*;

import services.BankService;

import java.awt.*;
import java.awt.event.*;

public class DepositFrame
        extends JFrame
        implements ActionListener {

    private JTextField amountField;

    private JButton depositButton;

    private long accountNumber;

    private DashboardFrame dashboardFrame;

    private BankService bankService;

    // Colors
    private final Color BACKGROUND_COLOR =
            new Color(24, 26, 27);

    private final Color CARD_COLOR =
            new Color(40, 44, 52);

    private final Color BUTTON_COLOR =
            new Color(0, 120, 215);

    private final Color TEXT_COLOR =
            Color.WHITE;

    public DepositFrame(
            long accountNumber,
            DashboardFrame dashboardFrame) {

        this.accountNumber =
                accountNumber;

        this.dashboardFrame =
                dashboardFrame;

        bankService =
                new BankService();

        // Frame
        setTitle("Deposit Money");

        setSize(450, 300);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(
                BACKGROUND_COLOR);

        setLayout(new GridBagLayout());

        // Main Card
        JPanel card =
                new JPanel();

        card.setPreferredSize(
                new Dimension(350, 220));

        card.setBackground(CARD_COLOR);

        card.setLayout(null);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(70,70,70),
                                1),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        // Title
        JLabel title =
                new JLabel("Deposit Money");

        title.setBounds(80, 20, 250, 35);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        title.setForeground(TEXT_COLOR);

        card.add(title);

        // Amount Label
        JLabel amountLabel =
                new JLabel("Enter Amount");

        amountLabel.setBounds(
                30,
                90,
                150,
                25);

        amountLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        amountLabel.setForeground(TEXT_COLOR);

        card.add(amountLabel);

        // Amount Field
        amountField =
                new JTextField();

        amountField.setBounds(
                30,
                120,
                280,
                40);

        amountField.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        16));

        amountField.setBackground(
                new Color(60,63,65));

        amountField.setForeground(
                Color.WHITE);

        amountField.setCaretColor(
                Color.WHITE);

        amountField.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));

        card.add(amountField);

        // Deposit Button
        depositButton =
                new JButton("Deposit");

        depositButton.setBounds(
                90,
                180,
                160,
                40);

        depositButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        depositButton.setBackground(
                BUTTON_COLOR);

        depositButton.setForeground(
                Color.WHITE);

        depositButton.setFocusPainted(false);

        depositButton.setBorderPainted(false);

        depositButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        depositButton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        depositButton.setBackground(
                                new Color(0,150,255));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        depositButton.setBackground(
                                BUTTON_COLOR);
                    }
                });

        depositButton.addActionListener(this);

        card.add(depositButton);

        add(card);

        setVisible(true);
    }

    @Override
    public void actionPerformed(
            ActionEvent e) {

        try {

            double amount =
                    Double.parseDouble(
                            amountField.getText());

            boolean success =
                bankService.depositMoney(
                        accountNumber,
                        amount);

            if (success) {

                dashboardFrame.refreshAccountData();


                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Failed!");
            }

            dashboardFrame.refreshAccountData();

            JOptionPane.showMessageDialog(
                    this,
                    "Deposit Successful!");

            dispose();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Amount!");
        }
    }
}