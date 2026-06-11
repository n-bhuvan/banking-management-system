package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.Account;
import utils.DatabaseConnection;

public class DatabaseService {

    // Save Account To Database
    public void saveAccount(Account account) {

        String query =
                "INSERT INTO accounts VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setInt(
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

            System.out.println(
                    "Account Saved To Database!");

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

    public void updateBalance(int accountNumber,
                          double newBalance) {

        String query =
                "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setDouble(1, newBalance);

            statement.setInt(2, accountNumber);

            statement.executeUpdate();

            System.out.println(
                    "Database Balance Updated!");

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}