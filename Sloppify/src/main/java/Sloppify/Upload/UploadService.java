package Sloppify.Upload;

import Sloppify.Track.SloppifyTrack;
import Sloppify.Track.SloppifyTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

@Service
public class UploadService {

    private final SloppifyTrackService sloppifyTrackService;
    private final String trackStorage;
    private final String coverStorage;

    @Autowired
    public UploadService(SloppifyTrackService sloppifyTrackService) {
        this.sloppifyTrackService = sloppifyTrackService;
        // Load storage properties
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            trackStorage = properties.getProperty("storage.track");
            coverStorage = properties.getProperty("storage.cover");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String upload(
            String title,
            String album,
            String artist,
            String releaseDate,
            MultipartFile trackFile,
            MultipartFile coverArt
    ) {

        //Create Track instance
        SloppifyTrack track = new SloppifyTrack(title, album, artist, releaseDate);

        // Define track pathname
        String trackExtension = this.getExtension(trackFile.getOriginalFilename()).orElseThrow();
        String trackPathname = trackStorage + track.getId() + trackExtension;
        track.setFilePath(trackPathname);

        // Define cover pathname
        String coverExtension = this.getExtension((coverArt.getOriginalFilename())).orElseThrow();
        String coverPathname = coverStorage + track.getId() + coverExtension;
        track.setCoverArt(coverPathname);

        //Store track file and cover art
        File trackDestination = new File(trackPathname);
        File coverDestination = new File(coverPathname);
        try {
            trackFile.transferTo(trackDestination);
            coverArt.transferTo(coverDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Save track data in repository
        return sloppifyTrackService.save(track);
    }

    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }
}
