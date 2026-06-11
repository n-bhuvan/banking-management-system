package models;
import java.io.Serializable;
public class Transaction implements Serializable {

    private String type;
    private double amount;

    // Constructor
    public Transaction(String type, double amount) {

        this.type = type;
        this.amount = amount;
    }

    // Display Transaction
    public void displayTransaction() {

        System.out.println(type + " : " + amount);
    }
}