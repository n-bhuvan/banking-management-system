package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String type;
    private double amount;
    private LocalDateTime timestamp;

    // Constructor
    public Transaction(String type, double amount) {

        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // Display Transaction
    public void displayTransaction() {

        System.out.println(
                type +
                " | Amount: " + amount +
                " | Time: " + timestamp);
    }
}