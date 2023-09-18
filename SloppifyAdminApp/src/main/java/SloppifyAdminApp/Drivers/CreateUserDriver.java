package SloppifyAdminApp.Drivers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class CreateUserDriver {

    private final String databaseUrl;
    private final String databaseUser;
    private final String databasePassword;

    public CreateUserDriver() {
        // Load database properties
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            databaseUrl = properties.getProperty("db.url");
            databaseUser = properties.getProperty("db.user");
            databasePassword = properties.getProperty("db.password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String email, String password, String username, String role) {
        try {
            // Create connection
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

            // Create statement and query
            Statement statement = connection.createStatement();
            String query =
                    "INSERT INTO `sloppify`.`sloppify_user`" +
                            "(`enabled`," +
                            "`locked`," +
                            "`email`," +
                            "`hashed_password`," +
                            "`role`," +
                            "`username`)" +
                            "VALUES" +
                            "(" +
                            true + ","+
                            false + "," +
                            "'" + email + "'," +
                            "'" + password + "'," +
                            "'" + role + "'," +
                            "'" + username + "');";

            // Execute query
            statement.execute(query);

            // Close connection
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean findUser(String email) {
        try {
            // Create connection
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

            // Create statement and query
            Statement statement = connection.createStatement();
            String query =
                    "SELECT * FROM `sloppify`.`sloppify_user`" +
                            "WHERE " +
                            "email = '" + email + "';";

            // Execute query
            ResultSet resultSet = statement.executeQuery(query);

            // Does track exist?
            boolean foundTrack = resultSet.next();

            // Close connection
            connection.close();

            return foundTrack;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
