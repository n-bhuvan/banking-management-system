package services;

import java.sql.*;

import utils.DatabaseConnection;
import security.PasswordHasher;

public class AdminService {

    public boolean login(
            String username,
            String password) {

        String query =
                "SELECT * FROM admins "
                + "WHERE username = ? "
                + "AND password = SHA2(?,256)";

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, username);

            statement.setString(2, password);

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}