package Sloppify.Upload;

import Sloppify.Track.SloppifyTrack;
import Sloppify.Track.SloppifyTrackService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {

    @Mock
    private SloppifyTrackService mockService;

    @InjectMocks
    private UploadService underTest;

    // Define upload parameters
    private String title;
    private String album ;
    private String artist;
    private String releaseDate;

    @Mock
    private MultipartFile mockTrackFile;

    @Mock
    private MultipartFile mockCoverArt;
    //

    private static String trackStorage;
    private static String coverStorage;

    @BeforeAll
    static void setupBeforeAll() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            trackStorage = properties.getProperty("storage.track");
            coverStorage = properties.getProperty("storage.cover");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setup() {
        title = "Title";
        album = "Album";
        artist= "Artist";
        releaseDate = "01/01/2000";
    }

    @Test
    void uploadCallsSaveWithRightPaths() {
        Mockito.when(mockTrackFile.getOriginalFilename()).thenReturn("trackFilePath.ext");
        Mockito.when(mockCoverArt.getOriginalFilename()).thenReturn("CoverArtPath.ext");
        underTest.upload(title, album, artist, releaseDate, mockTrackFile, mockCoverArt);
        ArgumentCaptor<SloppifyTrack> uploadCaptor = ArgumentCaptor.forClass(SloppifyTrack.class);
        Mockito.verify(mockService).save(uploadCaptor.capture());
        SloppifyTrack capturedTrack = uploadCaptor.getValue();

        // Define expected track and cover art paths
        String expectedTrackPath = trackStorage + capturedTrack.getId() + ".ext";
        String expectedCoverPath = coverStorage + capturedTrack.getId() + ".ext";

        assertEquals(expectedTrackPath, capturedTrack.getFilePath());
        assertEquals(expectedCoverPath, capturedTrack.getCoverArt());
    }
}
