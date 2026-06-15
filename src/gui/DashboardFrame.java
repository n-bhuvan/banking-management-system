package gui;

import javax.swing.*;

import models.Account;
import services.BankService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame
        extends JFrame
        implements ActionListener {

    private Account currentAccount;

    private JPanel sidebarPanel;
    private JPanel contentPanel;

    private JLabel welcomeLabel;
    private JLabel balanceLabel;

    private JButton balanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton transactionButton;

    private BankService bankService;

    private final Color SIDEBAR_COLOR =
        new Color(33, 37, 41);

    private final Color BACKGROUND_COLOR =
        new Color(24, 26, 27);

    private final Color BUTTON_COLOR =
        new Color(0, 123, 255);

    private final Color TEXT_COLOR =
        Color.WHITE;

    public DashboardFrame(Account account) {

        currentAccount = account;

        bankService = new BankService();

        // Frame
        setTitle("Smart Banking Dashboard");

        setSize(900, 550);

        setLayout(new BorderLayout());

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(
        BACKGROUND_COLOR);
        // Sidebar
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(
        SIDEBAR_COLOR);

        sidebarPanel.setLayout(
                new GridLayout(6, 1, 10, 10));

        sidebarPanel.setPreferredSize(
                new Dimension(220, 0));

        sidebarPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        15,
                        20,
                        15));

        // Buttons
        balanceButton =
                createSidebarButton("Check Balance");

        depositButton =
                createSidebarButton("Deposit");

        withdrawButton =
                createSidebarButton("Withdraw");

        transferButton =
                createSidebarButton("Transfer");

        transactionButton =
                createSidebarButton("Transactions");

        sidebarPanel.add(balanceButton);

        sidebarPanel.add(depositButton);

        sidebarPanel.add(withdrawButton);

        sidebarPanel.add(transferButton);

        sidebarPanel.add(transactionButton);

        add(sidebarPanel, BorderLayout.WEST);

        // Main Content
        contentPanel = new JPanel();
        contentPanel.setBackground(
        BACKGROUND_COLOR);

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS));

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        40,
                        40,
                        40));

        // Welcome Label
        welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + currentAccount
                                .getAccountHolderName());
        welcomeLabel.setForeground(
        TEXT_COLOR);

        welcomeLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        // Balance Label
        balanceLabel =
                new JLabel(
                        "Current Balance: "
                                + currentAccount
                                .getBalance());

        balanceLabel.setForeground(
        TEXT_COLOR);

        balanceLabel.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        22));

        balanceLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        40,
                        0,
                        0,
                        0));

        JPanel welcomeCard =
        createDashboardCard(
                "Account Holder",
                currentAccount
                        .getAccountHolderName());

        welcomeCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120));

        JPanel balanceCard =
                createDashboardCard(
                        "Current Balance",
                        String.valueOf(
                                currentAccount
                                        .getBalance()));

        balanceCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        120));

        JPanel profileCard =
                createProfileCard();

        profileCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        220));

        contentPanel.add(profileCard);

        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Create Styled Sidebar Button
    private JButton createSidebarButton(
        String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        // Default Style
        button.setBackground(
                BUTTON_COLOR);

        button.setForeground(
                TEXT_COLOR);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        button.addMouseListener(
                new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                        button.setBackground(
                                new Color(0, 150, 255));
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                        button.setBackground(
                                BUTTON_COLOR);
                        }
                });

        button.addActionListener(this);

        return button;
        }

    private JPanel createDashboardCard(
                String title,
                String value) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS));

        card.setBackground(
                new Color(40, 44, 52));

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(70, 70, 70),
                                1),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        // Title
                JLabel titleLabel =
                        new JLabel(title);

                titleLabel.setFont(
                        new Font("Arial",
                                Font.BOLD,
                                18));

                titleLabel.setForeground(
                        Color.LIGHT_GRAY);

                // Value
                JLabel valueLabel =
                        new JLabel(value);

                valueLabel.setFont(
                        new Font("Arial",
                                Font.BOLD,
                                26));

                valueLabel.setForeground(
                        Color.WHITE);

                valueLabel.setBorder(
                        BorderFactory.createEmptyBorder(
                                15,
                                0,
                                0,
                                0));

                card.add(titleLabel);

                card.add(valueLabel);

                return card;
                }
                private JPanel createProfileCard() {

        JPanel card =
                new JPanel();

        card.setLayout(
                new GridLayout(4, 1, 10, 10));

        card.setBackground(
                new Color(40, 44, 52));

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(70, 70, 70),
                                1),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        JLabel accountLabel =
                new JLabel(
                        "Account Number: "
                        + currentAccount
                        .getAccountNumber());

        JLabel nameLabel =
                new JLabel(
                        "Holder Name: "
                        + currentAccount
                        .getAccountHolderName());

        JLabel roleLabel =
                new JLabel(
                        "Role: "
                        + currentAccount
                        .getRole());

        JLabel balanceLabel =
                new JLabel(
                        "Balance: ₹"
                        + currentAccount
                        .getBalance());

        JLabel[] labels = {

                accountLabel,
                nameLabel,
                roleLabel,
                balanceLabel
        };

        for (JLabel label : labels) {

                label.setFont(
                        new Font("Arial",
                                Font.BOLD,
                                18));

                label.setForeground(
                        Color.WHITE);

                card.add(label);
        }

        return card;
        }

    // Refresh Data
   public void refreshAccountData() {

        BankService bankService =
                new BankService();

        currentAccount =
                bankService.getUpdatedAccount(
                        currentAccount
                                .getAccountNumber());

        contentPanel.removeAll();

        JPanel profileCard =
                createProfileCard();

        profileCard.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        220));

        contentPanel.add(profileCard);

        contentPanel.revalidate();

        contentPanel.repaint();
        }

    @Override
    public void actionPerformed(
            ActionEvent e) {

        // Balance
        if (e.getSource()
                == balanceButton) {

            refreshAccountData();

            JOptionPane.showMessageDialog(
                    this,
                    "Balance: "
                            + currentAccount
                            .getBalance());
        }

        // Deposit
        else if (e.getSource()
                == depositButton) {

        new DepositFrame(
                currentAccount
                        .getAccountNumber(),
                this);
        }

        // Withdraw
        else if (e.getSource()
                == withdrawButton) {

                new WithdrawFrame(
                        currentAccount
                                .getAccountNumber(),
                        this);
        }

        // Transfer
        else if (e.getSource()
                == transferButton) {

        new TransferFrame(
                currentAccount
                        .getAccountNumber(),
                this);
        }

        // Transactions
        else if (e.getSource()
                == transactionButton) {

            new TransactionHistoryFrame(
                currentAccount);
        }
    }
}