package services;

import java.util.ArrayList;

import models.Account;
import utils.FileHandler;

public class BankService {

    // Load Saved Accounts
    private ArrayList<Account> accounts;

    // Constructor
    public BankService() {

        accounts = FileHandler.loadAccounts();

        System.out.println("Loaded Accounts: " + accounts.size());
    }

    // Create Account
    public void createAccount(int accountNumber,
                              String accountHolderName,
                              double balance) {

        Account newAccount =
                new Account(accountNumber,
                            accountHolderName,
                            balance);

        accounts.add(newAccount);

        FileHandler.saveAccounts(accounts);

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

            FileHandler.saveAccounts(accounts);

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

            FileHandler.saveAccounts(accounts);

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

    // Show Transactions
    public void showTransactions(int accountNumber) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.showTransactionHistory();

        } else {

            System.out.println("Account Not Found!");
        }
    }
}