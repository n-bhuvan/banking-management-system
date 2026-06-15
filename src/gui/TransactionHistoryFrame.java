package gui;

import javax.swing.*;

import java.awt.*;

import models.Account;
import models.Transaction;

public class TransactionHistoryFrame
        extends JFrame {

    public TransactionHistoryFrame(
            Account account) {

        setTitle("Transaction History");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Title
        JLabel title =
                new JLabel(
                        "Transaction History",
                        SwingConstants.CENTER);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        22));

        add(title, BorderLayout.NORTH);

        // Text Area
        JTextArea historyArea =
                new JTextArea();

        historyArea.setEditable(false);

        historyArea.setFont(
                new Font("Monospaced",
                        Font.PLAIN,
                        14));

        // Load Transactions
        StringBuilder builder =
                new StringBuilder();

        for (Transaction transaction
                : account.getTransactionHistory()) {

            builder.append(transaction)
                    .append("\n\n");
        }

        if (builder.length() == 0) {

            builder.append(
                    "No Transactions Found!");
        }

        historyArea.setText(
                builder.toString());

        JScrollPane scrollPane =
                new JScrollPane(historyArea);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}