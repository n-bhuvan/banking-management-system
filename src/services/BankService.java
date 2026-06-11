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
                          double balance,
                          String password,
                          String role) {

    // Duplicate Check
        if (findAccount(accountNumber) != null) {

            System.out.println(
                    "Account Number Already Exists!");

            return;
        }

        Account newAccount =
                new Account(accountNumber,
                            accountHolderName,
                            balance,
                            password,
                            role);

        accounts.add(newAccount);

        FileHandler.saveAccounts(accounts);

        System.out.println(
                "Account Created Successfully!");
    }

    public boolean login(int accountNumber,
                        String password) {

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            return false;
        }

        return account.getPassword()
                .equals(password);
    }

    public boolean isAdmin(int accountNumber) {

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            return false;
        }

        return account.getRole()
                .equalsIgnoreCase("admin");
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
    public void transferMoney(int senderAccountNumber,
                          int receiverAccountNumber,
                          double amount) {

        Account sender =
                findAccount(senderAccountNumber);

        Account receiver =
                findAccount(receiverAccountNumber);

        // Validate Accounts
        if (sender == null) {

            System.out.println("Sender Account Not Found!");
            return;
        }

        if (receiver == null) {

            System.out.println("Receiver Account Not Found!");
            return;
        }

        // Prevent Self Transfer
        if (senderAccountNumber ==
                receiverAccountNumber) {

            System.out.println(
                    "Cannot Transfer To Same Account!");

            return;
        }

        // Validate Amount
        if (amount <= 0) {

            System.out.println(
                    "Invalid Transfer Amount!");

            return;
        }

        // Check Balance
        if (sender.getBalance() < amount) {

            System.out.println(
                    "Insufficient Balance!");

            return;
        }

        // Transfer Logic
        sender.withdraw(amount);

        receiver.deposit(amount);

        // Advanced Transaction Logs
        sender.addTransaction(
                "Transferred To Account "
                        + receiverAccountNumber,
                amount);

        receiver.addTransaction(
                "Received From Account "
                        + senderAccountNumber,
                amount);

        // Save Accounts
        FileHandler.saveAccounts(accounts);

        System.out.println(
                "Money Transfer Successful!");
    }
}

