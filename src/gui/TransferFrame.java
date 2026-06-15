package gui;

import javax.swing.*;

import services.BankService;

import java.awt.*;
import java.awt.event.*;

public class TransferFrame
        extends JFrame
        implements ActionListener {

    private JTextField receiverField;

    private JTextField amountField;

    private JButton transferButton;

    private long senderAccountNumber;

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

    public TransferFrame(
            long senderAccountNumber,
            DashboardFrame dashboardFrame) {

        this.senderAccountNumber =
                senderAccountNumber;

        this.dashboardFrame =
                dashboardFrame;

        bankService =
                new BankService();

        // Frame
        setTitle("Transfer Money");

        setSize(500, 380);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE);

        getContentPane().setBackground(
                BACKGROUND_COLOR);

        setLayout(new GridBagLayout());

        // Card
        JPanel card =
                new JPanel();

        card.setPreferredSize(
                new Dimension(380, 280));

        card.setBackground(CARD_COLOR);

        card.setLayout(null);

        // Title
        JLabel title =
                new JLabel("Transfer Money");

        title.setBounds(
                80,
                20,
                250,
                35);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        title.setForeground(TEXT_COLOR);

        card.add(title);

        // Receiver Label
        JLabel receiverLabel =
                new JLabel("Receiver Account");

        receiverLabel.setBounds(
                30,
                80,
                200,
                25);

        receiverLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        receiverLabel.setForeground(
                TEXT_COLOR);

        card.add(receiverLabel);

        // Receiver Field
        receiverField =
                new JTextField();

        receiverField.setBounds(
                30,
                110,
                300,
                40);

        receiverField.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        16));

        receiverField.setBackground(
                new Color(60,63,65));

        receiverField.setForeground(
                Color.WHITE);

        receiverField.setCaretColor(
                Color.WHITE);

        card.add(receiverField);

        // Amount Label
        JLabel amountLabel =
                new JLabel("Transfer Amount");

        amountLabel.setBounds(
                30,
                160,
                200,
                25);

        amountLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        amountLabel.setForeground(
                TEXT_COLOR);

        card.add(amountLabel);

        // Amount Field
        amountField =
                new JTextField();

        amountField.setBounds(
                30,
                190,
                300,
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

        card.add(amountField);

        // Button
        transferButton =
                new JButton("Transfer");

        transferButton.setBounds(
                110,
                245,
                150,
                40);

        transferButton.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        transferButton.setBackground(
                BUTTON_COLOR);

        transferButton.setForeground(
                Color.WHITE);

        transferButton.setFocusPainted(false);

        transferButton.setBorderPainted(false);

        transferButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        // Hover
        transferButton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        transferButton.setBackground(
                                new Color(0,150,255));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        transferButton.setBackground(
                                BUTTON_COLOR);
                    }
                });

        transferButton.addActionListener(this);

        card.add(transferButton);

        add(card);

        setVisible(true);
    }

    @Override
    public void actionPerformed(
            ActionEvent e) {

        try {

            long receiverAccount =
                    Long.parseLong(
                            receiverField.getText());

            double amount =
                    Double.parseDouble(
                            amountField.getText());

            boolean success =
                    bankService.transferMoney(
                            senderAccountNumber,
                            receiverAccount,
                            amount);

            if (success) {

                dashboardFrame
                        .refreshAccountData();

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer Successful!");

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer Failed!");
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Input!");
        }
    }
}