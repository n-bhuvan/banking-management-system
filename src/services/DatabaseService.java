package services;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}