package models;

import java.io.Serializable;

import java.util.ArrayList;

public class Account implements Serializable {


    // Variables
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String password;
    private String role;

    // Transaction List
    private ArrayList<Transaction> transactions = new ArrayList<>();

    // Constructor
    public Account(int accountNumber,
               String accountHolderName,
               double balance,
               String password,
               String role) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.password = password;
        this.role = role;
    }

    // Deposit Method
    public void deposit(double amount) {

        if (amount > 0) {

            balance += amount;

            transactions.add(new Transaction("Deposit", amount));

            System.out.println("Amount Deposited Successfully!");

        } else {

            System.out.println("Invalid Deposit Amount!");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {

        if (amount <= 0) {

            System.out.println("Invalid Withdrawal Amount!");

        } else if (amount > balance) {

            System.out.println("Insufficient Balance!");

        } else {

            balance -= amount;

            transactions.add(new Transaction("Withdrawal", amount));

            System.out.println("Withdrawal Successful!");
        }
    }

    // Display Account Details
    public void displayAccountDetails() {

        System.out.println("\n----- Account Details -----");

        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
    }

    // Show Transaction History
    public void showTransactionHistory() {

        System.out.println("\n----- Transaction History -----");

        if (transactions.isEmpty()) {

            System.out.println("No Transactions Found!");

        } else {

            for (Transaction transaction : transactions) {

                transaction.displayTransaction();
            }
        }
    }
    public void addTransaction(String type,
                           double amount) {

        transactions.add(
                new Transaction(type, amount));
    }

    // Getter for Account Number
    public int getAccountNumber() {

        return accountNumber;
    }

    // Getter for Balance
    public double getBalance() {

        return balance;
    }
    public String getPassword() {

        return password;
    }
    public String getRole() {

        return role;
    }
}