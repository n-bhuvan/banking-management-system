package models;

import java.io.Serializable;

import java.util.ArrayList;
import models.Transaction;

public class Account implements Serializable {


    // Variables
    private long accountNumber;
    private String accountHolderName;
    private double balance;
    private String password;
    private String role;

    // Transaction List
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();
    

    // Constructor
    public Account(long accountNumber,
               String accountHolderName,
               double balance,
               String password,
               String role) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.password = password;
        this.role = role;
        this.transactionHistory = new ArrayList<>();
    }

    // Deposit Method
    public void deposit(double amount) {

        if (amount > 0) {

            balance += amount;

            transactionHistory.add(new Transaction("Deposit", amount, java.time.LocalDate.now().toString()));

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

            transactionHistory.add(new Transaction("Withdrawal", amount, java.time.LocalDate.now().toString()));

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

        if (transactionHistory.isEmpty()) {

            System.out.println("No Transactions Found!");

        } else {

            for (Transaction transaction : transactionHistory) {

                System.out.println(transaction);
            }
        }
    }
    public void addTransaction(String type,
                           double amount) {

        transactionHistory.add(
                new Transaction(type, amount, java.time.LocalDate.now().toString()));
    }
    public void setTransactionHistory(
            ArrayList<Transaction>
                    transactionHistory) {

        this.transactionHistory =
                transactionHistory;
    }

    // Getter for Account Number
    public long getAccountNumber() {
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
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public ArrayList<Transaction>
        getTransactionHistory() {

            return transactionHistory;
    }
    

}