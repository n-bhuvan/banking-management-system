package gui;

import javax.swing.*;

import services.AdminService;

import java.awt.*;
import java.awt.event.*;

public class AdminLoginFrame
        extends JFrame
        implements ActionListener {

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JButton loginButton;

    private AdminService adminService;

    // Colors
    private final Color BACKGROUND_COLOR =
            new Color(24,26,27);

    private final Color CARD_COLOR =
            new Color(40,44,52);

    private final Color BUTTON_COLOR =
            new Color(220,53,69);

    private final Color TEXT_COLOR =
            Color.WHITE;

    public AdminLoginFrame() {

        adminService =
                new AdminService();

        // Frame
        setTitle("Admin Login");

        setSize(500, 500);

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
                new Dimension(380,380));

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
                new JLabel("Admin Login");

        title.setBounds(
                95,
                20,
                250,
                40);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        30));

        title.setForeground(
                TEXT_COLOR);

        card.add(title);

        // Username Label
        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setBounds(
                40,
                100,
                200,
                25);

        usernameLabel.setForeground(
                TEXT_COLOR);

        usernameLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        card.add(usernameLabel);

        // Username Field
        usernameField =
                new JTextField();

        usernameField.setBounds(
                40,
                130,
                280,
                40);

        styleTextField(usernameField);

        card.add(usernameField);

        // Password Label
        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setBounds(
                40,
                200,
                200,
                25);

        passwordLabel.setForeground(
                TEXT_COLOR);

        passwordLabel.setFont(
                new Font("Arial",
                        Font.BOLD,
                        16));

        card.add(passwordLabel);

        // Password Field
        passwordField =
                new JPasswordField();

        passwordField.setBounds(
                40,
                230,
                280,
                40);

        styleTextField(passwordField);

        card.add(passwordField);

        // Login Button
        loginButton =
                createStyledButton(
                        "Admin Login");

        loginButton.setBounds(
                95,
                310,
                180,
                45);

        card.add(loginButton);

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

    // Styled Buttons
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
                                new Color(255,80,80));
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

        String username =
                usernameField.getText();

        String password =
                String.valueOf(
                        passwordField
                                .getPassword());

        boolean success =
                adminService.login(
                        username,
                        password);

        if (success) {

            new AdminDashboardFrame();

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Admin Credentials!");
        }
    }
}