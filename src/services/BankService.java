package services;

import java.util.ArrayList;

import models.Account;

public class BankService {

    // Store Accounts
    private ArrayList<Account> accounts = new ArrayList<>();

    // Create Account
    public void createAccount(int accountNumber,
                              String accountHolderName,
                              double balance) {

        Account newAccount =
                new Account(accountNumber,
                            accountHolderName,
                            balance);

        accounts.add(newAccount);

        System.out.println("Account Created Successfully!");
    }

    // Find Account
    public Account findAccount(int accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber() == accountNumber) {

                return account;
            }
        }

        return null;
    }

    // Deposit Money
    public void depositMoney(int accountNumber,
                             double amount) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.deposit(amount);

        } else {

            System.out.println("Account Not Found!");
        }
    }

    // Withdraw Money
    public void withdrawMoney(int accountNumber,
                              double amount) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.withdraw(amount);

        } else {

            System.out.println("Account Not Found!");
        }
    }

    // Display Account
    public void displayAccount(int accountNumber) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.displayAccountDetails();

        } else {

            System.out.println("Account Not Found!");
        }
    }

    // Show Transaction History
    public void showTransactions(int accountNumber) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.showTransactionHistory();

        } else {

            System.out.println("Account Not Found!");
        }
    }
}