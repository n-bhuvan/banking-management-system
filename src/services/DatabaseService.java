package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.Account;
import models.Transaction;
import utils.DatabaseConnection;
import java.sql.ResultSet;
public class DatabaseService {

    // Save Account To Database
    public void saveAccount(Account account) {

        String query =
                "INSERT INTO accounts "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query);

                statement.setLong(
                        1,
                        account.getAccountNumber());

                statement.setString(
                        2,
                        account.getAccountHolderName());

                statement.setDouble(
                        3,
                        account.getBalance());

                statement.setString(
                        4,
                        account.getPassword());

                statement.setString(
                        5,
                        account.getRole());

                statement.executeUpdate();

                connection.close();

        } catch (Exception e) {

                e.printStackTrace();
        }
        }

    public ArrayList<Account> loadAccounts() {

        ArrayList<Account> accounts =
                new ArrayList<>();

        String query =
                "SELECT * FROM accounts";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            ResultSet resultSet =
                    statement.executeQuery();

            while (resultSet.next()) {

                int accountNumber =
                        resultSet.getInt(
                                "account_number");

                String accountHolderName =
                        resultSet.getString(
                                "account_holder_name");

                double balance =
                        resultSet.getDouble(
                                "balance");

                String password =
                        resultSet.getString(
                                "password");

                String role =
                        resultSet.getString(
                                "role");

                Account account =
                        new Account(
                                accountNumber,
                                accountHolderName,
                                balance,
                                password,
                                role);

                accounts.add(account);
            }

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return accounts;
    }

    public void updateBalance(
                long accountNumber,
                double newBalance) {

        String query =
                "UPDATE accounts "
                + "SET balance = ? "
                + "WHERE account_number = ?";

        try {

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query);

                statement.setDouble(
                        1,
                        newBalance);

                statement.setLong(
                        2,
                        accountNumber);

                statement.executeUpdate();

                connection.close();

        } catch (Exception e) {

                e.printStackTrace();
        }
        }
    public void saveTransaction(
                long accountNumber,
                String type,
                double amount,
                String date) {

        String query =
                "INSERT INTO transactions "
                + "(account_number, type, amount, transaction_date) "
                + "VALUES (?, ?, ?, ?)";

        try {

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query);

                statement.setLong(
                        1,
                        accountNumber);

                statement.setString(
                        2,
                        type);

                statement.setDouble(
                        3,
                        amount);

                statement.setString(
                        4,
                        date);

                statement.executeUpdate();

                connection.close();

        } catch (Exception e) {

                e.printStackTrace();
        }
        }
        public java.util.ArrayList<Transaction>
        getTransactions(
                long accountNumber) {

                java.util.ArrayList<Transaction>
                        transactions =
                        new java.util.ArrayList<>();

                String query =
                        "SELECT * FROM transactions "
                        + "WHERE account_number = ?";

                try {

                        Connection connection =
                                DatabaseConnection.getConnection();

                        PreparedStatement statement =
                                connection.prepareStatement(query);

                        statement.setLong(
                                1,
                                accountNumber);

                        ResultSet resultSet =
                                statement.executeQuery();

                        while (resultSet.next()) {

                        Transaction transaction =
                                new Transaction(

                                        resultSet.getString(
                                                "type"),

                                        resultSet.getDouble(
                                                "amount"),

                                        resultSet.getString(
                                                "transaction_date"));

                        transactions.add(transaction);
                        }

                        connection.close();

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return transactions;
        }
        public Account getAccountFromDatabase(
                long accountNumber) {

        String query =
                "SELECT * FROM accounts "
                + "WHERE account_number = ?";

        try {

                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(query);

                statement.setLong(
                        1,
                        accountNumber);

                ResultSet resultSet =
                        statement.executeQuery();

                if (resultSet.next()) {

                Account account =
                        new Account(

                                resultSet.getLong(
                                        "account_number"),

                                resultSet.getString(
                                        "account_holder_name"),

                                resultSet.getDouble(
                                        "balance"),

                                resultSet.getString(
                                        "password"),

                                resultSet.getString(
                                        "role"));

                account.setTransactionHistory(
                        getTransactions(
                                accountNumber));

                connection.close();

                return account;
                }

                connection.close();

        } catch (Exception e) {

                e.printStackTrace();
        }

        return null;
        }
}