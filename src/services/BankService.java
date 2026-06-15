package services;

import java.util.ArrayList;

import utils.FileHandler;
import models.Account;
import services.DatabaseService;
import security.PasswordHasher;

public class BankService {

    // Load Saved Accounts

    // Constructor
    public BankService() {

        DatabaseService databaseService =
                new DatabaseService();


        System.out.println(
                "Accounts Loaded From Database!");
    }

    // Create Account
    public long createAccount(
        String accountHolderName,
        double balance,
        String password,String role) {

    // Generate Account Number
        long generatedAccountNumber =
                20260000L
                + (long)(Math.random() * 9000);

        // Hash Password
        String hashedPassword =
                PasswordHasher.hashPassword(
                        password);

        // Create Account Object
        Account newAccount =
                new Account(
                        generatedAccountNumber,
                        accountHolderName,
                        balance,
                        hashedPassword,
                        "user");

        DatabaseService databaseService =
                new DatabaseService();

        databaseService.saveAccount(
                newAccount);

        return generatedAccountNumber;
    }

    public boolean login(long accountNumber,
                        String password) {

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            return false;
        }

        String hashedPassword =
                PasswordHasher.hashPassword(password);

        return account.getPassword()
                .equals(hashedPassword);
    }

    public boolean isAdmin(long accountNumber) {

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            return false;
        }

        return account.getRole()
                .equalsIgnoreCase("admin");
    }

    // Find Account
    public Account findAccount(
            long accountNumber) {

        DatabaseService databaseService =
                new DatabaseService();

        return databaseService
                .getAccountFromDatabase(
                        accountNumber);
    }
    public Account getUpdatedAccount(
            long accountNumber) {

        DatabaseService databaseService =
                new DatabaseService();

        return databaseService
                .getAccountFromDatabase(
                        accountNumber);
    }

    // Deposit Money
    public boolean depositMoney(
            long accountNumber,
            double amount) {

        DatabaseService databaseService =
                new DatabaseService();

        Account account =
                databaseService
                        .getAccountFromDatabase(
                                accountNumber);

        if (account != null) {

            double newBalance =
                    account.getBalance()
                    + amount;

            databaseService.updateBalance(
                    accountNumber,
                    newBalance);

            databaseService.saveTransaction(
                    accountNumber,
                    "Deposit",
                    amount,
                    java.time.LocalDateTime
                            .now()
                            .toString());

            return true;
        }

        return false;
    }
    // Withdraw Money
    public boolean withdrawMoney(
            long accountNumber,
            double amount) {

        DatabaseService databaseService =
                new DatabaseService();

        Account account =
                databaseService
                        .getAccountFromDatabase(
                                accountNumber);

        if (account != null) {

            if (amount <= account.getBalance()) {

                double newBalance =
                        account.getBalance()
                        - amount;

                databaseService.updateBalance(
                        accountNumber,
                        newBalance);

                databaseService.saveTransaction(
                        accountNumber,
                        "Withdraw",
                        amount,
                        java.time.LocalDateTime
                                .now()
                                .toString());

                return true;
            }
        }

        return false;
    }

    // Display Account
    public void displayAccount(long accountNumber) {

        Account account = findAccount(accountNumber);

        if (account != null) {

            account.displayAccountDetails();

        } else {

            System.out.println("Account Not Found!");
        }
    }

    // Show Transactions
    public boolean transferMoney(
            long senderAccountNumber,
            long receiverAccountNumber,
            double amount) {

        DatabaseService databaseService =
                new DatabaseService();

        Account sender =
                databaseService
                        .getAccountFromDatabase(
                                senderAccountNumber);

        Account receiver =
                databaseService
                        .getAccountFromDatabase(
                                receiverAccountNumber);

        if (sender != null
                && receiver != null) {

            if (amount <= sender.getBalance()) {

                double senderBalance =
                        sender.getBalance()
                        - amount;

                double receiverBalance =
                        receiver.getBalance()
                        + amount;

                databaseService.updateBalance(
                        senderAccountNumber,
                        senderBalance);

                databaseService.updateBalance(
                        receiverAccountNumber,
                        receiverBalance);

                databaseService.saveTransaction(
                        senderAccountNumber,
                        "Transfer Sent",
                        amount,
                        java.time.LocalDateTime
                                .now()
                                .toString());

                databaseService.saveTransaction(
                        receiverAccountNumber,
                        "Transfer Received",
                        amount,
                        java.time.LocalDateTime
                                .now()
                                .toString());

                return true;
            }
        }

        return false;
    }

    public double getTotalBankBalance() {

        double total = 0;

        return total;
    }

    public int getTotalUsers() {

        int count = 0;


        return count;
    }

    public int getTotalAdmins() {

        int count = 0;


        return count;
    }
}

