package SloppifyAdminApp.Drivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

import SloppifyAdminApp.Identifier.Identifier;

public class UploadTrackDriver {

    private final String databaseUrl;
    private final String databaseUser;
    private final String databasePassword;
    private final String trackStorage;
    private final String coverStorage;

    private final Identifier identifier;

    public UploadTrackDriver(Identifier identifier) {
        // Set id creator
        this.identifier = identifier;

        // Load database and storage properties
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            databaseUrl = properties.getProperty("db.url");
            databaseUser = properties.getProperty("db.user");
            databasePassword = properties.getProperty("db.password");
            trackStorage = properties.getProperty("storage.track");
            coverStorage = properties.getProperty("storage.cover");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadTrack(
            String title,
            String album,
            String artist,
            String releaseDate,
            String trackFilePath,
            String coverArtPath
    ) {
        // Create id to identify track in database and app storage
        String id = identifier.createIdentifier();

        // Create a copy of track file in app storage
        String trackExtension = this.getExtension(trackFilePath).orElseThrow();
        String trackDestination = trackStorage + id + trackExtension;
        File trackOriginal = new File(trackFilePath);
        File trackCopy = new File(trackDestination);
        try {
            Files.copy(trackOriginal.toPath(), trackCopy.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a copy of cover art file in app storage
        String coverExtension = this.getExtension(coverArtPath).orElseThrow();
        String coverDestination = coverStorage + id + coverExtension;
        File coverOriginal = new File(coverArtPath);
        File coverCopy = new File(coverDestination);
        try {
            Files.copy(coverOriginal.toPath(), coverCopy.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Upload to repository
        try {
            // Create connection
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

            // Create statement and query
            Statement statement = connection.createStatement();
            String query =
                    "INSERT INTO `sloppify`.`sloppify_track`" +
                            "(`album`," +
                            "`artist`," +
                            "`cover_art`," +
                            "`file_path`," +
                            "`id`," +
                            "`release_date`," +
                            "`title`)" +
                            "VALUES" +
                            "(" +
                            "'" + album + "'," +
                            "'" + artist + "'," +
                            "'" + coverDestination + "'," +
                            "'" + trackDestination + "'," +
                            "'" + id + "'," +
                            "'" + releaseDate + "'," +
                            "'" + title + "');";

            // Execute query
            statement.execute(query);

            // Close connection
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean findTrack(String title, String album, String artist) {
        try {
            // Create connection
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

            // Create statement and query
            Statement statement = connection.createStatement();
            String query =
                    "SELECT * FROM `sloppify`.`sloppify_track`" +
                            "WHERE " +
                            "title = '" + title + "'" + "AND " +
                            "album = '" + album + "'" + "AND " +
                            "artist = '" + artist + "';";

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

    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }
}
