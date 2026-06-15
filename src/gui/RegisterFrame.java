package gui;

import javax.swing.*;

import services.BankService;

import java.awt.*;
import java.awt.event.*;

public class RegisterFrame
        extends JFrame
        implements ActionListener {

    private JTextField nameField;

    private JTextField balanceField;

    private JPasswordField passwordField;

    private JButton createButton;

    private BankService bankService;

    // Colors
    private final Color BACKGROUND_COLOR =
            new Color(24,26,27);

    private final Color CARD_COLOR =
            new Color(40,44,52);

    private final Color BUTTON_COLOR =
            new Color(0,120,215);

    private final Color TEXT_COLOR =
            Color.WHITE;

    public RegisterFrame() {

        bankService =
                new BankService();

        // Frame
        setTitle("Create Account");

        setSize(550, 620);

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
                new Dimension(420,520));

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
                new JLabel("Register Account");

        title.setBounds(
                75,
                20,
                300,
                40);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        30));

        title.setForeground(
                TEXT_COLOR);

        card.add(title);

        // Holder Name
        JLabel nameLabel =
                new JLabel("Holder Name");

        nameLabel.setBounds(
                40,
                100,
                200,
                25);

        nameLabel.setForeground(
                TEXT_COLOR);

        nameLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        card.add(nameLabel);

        nameField =
                new JTextField();

        nameField.setBounds(
                40,
                130,
                320,
                40);

        styleTextField(nameField);

        card.add(nameField);

        // Initial Balance
        JLabel balanceLabel =
                new JLabel("Initial Balance");

        balanceLabel.setBounds(
                40,
                200,
                200,
                25);

        balanceLabel.setForeground(
                TEXT_COLOR);

        balanceLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        card.add(balanceLabel);

        balanceField =
                new JTextField();

        balanceField.setBounds(
                40,
                230,
                320,
                40);

        styleTextField(balanceField);

        card.add(balanceField);

        // Password
        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setBounds(
                40,
                300,
                200,
                25);

        passwordLabel.setForeground(
                TEXT_COLOR);

        passwordLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        card.add(passwordLabel);

        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                40,
                330,
                320,
                40);

        styleTextField(passwordField);

        card.add(passwordField);

        // Create Button
        createButton =
                createStyledButton(
                        "Create Account");

        createButton.setBounds(
                110,
                430,
                180,
                45);

        card.add(createButton);

        add(card);

        setVisible(true);
    }

    // Style TextFields
    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font("Arial",
                        Font.PLAIN,
                        16));

        field.setBackground(
                new Color(60,63,65));

        field.setForeground(
                Color.WHITE);

        field.setCaretColor(
                Color.WHITE);

        field.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        10,
                        5,
                        10));
    }

    // Styled Button
    private JButton createStyledButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        button.setBackground(
                BUTTON_COLOR);

        button.setForeground(
                Color.WHITE);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                new Color(0,150,255));
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

    @Override
    public void actionPerformed(
            ActionEvent e) {

        try {

            String name =
                    nameField.getText();

            double balance =
                    Double.parseDouble(
                            balanceField.getText());

            String password =
                    String.valueOf(
                            passwordField
                                    .getPassword());

            String role =
                    "user";

            long generatedAccountNumber =
                    bankService.createAccount(
                            name,
                            balance,
                            password,
                            role);

            JTextArea textArea =
                    new JTextArea(

                            "Account Created Successfully!\n\n"

                            + "Generated Account Number:\n"

                            + generatedAccountNumber);

            textArea.setEditable(false);

            textArea.setFont(
                    new Font("Arial",
                            Font.BOLD,
                            16));

            JOptionPane.showMessageDialog(
                    this,
                    textArea,
                    "Account Created",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();

            new LoginFrame(
                    generatedAccountNumber);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Input!");
        }
    }
}