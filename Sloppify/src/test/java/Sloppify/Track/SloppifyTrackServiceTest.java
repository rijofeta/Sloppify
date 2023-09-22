package Sloppify.Track;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SloppifyTrackServiceTest {

    @Mock
    private SloppifyTrackRepository mockRepo;

    @InjectMocks
    private SloppifyTrackService underTest;

    private SloppifyTrack sloppifyTrack;

    @BeforeEach
    void setup() {
        sloppifyTrack = new SloppifyTrack(
                "Title",
                "Album",
                "Artist",
                "01/01/2000"
        );
        sloppifyTrack.setFilePath("C:/Track/Path");
        sloppifyTrack.setCoverArt("C:/Cover/Art");
    }

    @Test
    void saveCallsRepo() {
        underTest.save(sloppifyTrack);
        ArgumentCaptor<SloppifyTrack> saveCaptor = ArgumentCaptor.forClass(SloppifyTrack.class);
        Mockito.verify(mockRepo).save(saveCaptor.capture());
        SloppifyTrack capturedTrack = saveCaptor.getValue();
        assertEquals(sloppifyTrack, capturedTrack);
    }

    @Test
    void saveReturnsSuccessMessage() {
        String expectedMessage = "Uploaded track successfully";
        String message = underTest.save(sloppifyTrack);
        assertEquals(expectedMessage, message);
    }

    @Test
    void trackInfoCallsFindById() {
        Mockito.when(mockRepo.findById(sloppifyTrack.getId())).thenReturn(Optional.of(sloppifyTrack));
        underTest.trackInfo(sloppifyTrack.getId());
        ArgumentCaptor<String> findByIdCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(mockRepo).findById(findByIdCaptor.capture());
        String capturedId = findByIdCaptor.getValue();
        assertEquals(sloppifyTrack.getId(), capturedId);
    }

    @Test
    void trackInfoThrowsIllegalStateException() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> underTest.trackInfo(sloppifyTrack.getId())
        );
        assertEquals(exception.getMessage(), "Track not found");
    }

    @Test
    void trackInfoReturnsExpectedTrackInfo() {
        Mockito.when(mockRepo.findById(sloppifyTrack.getId())).thenReturn(Optional.of(sloppifyTrack));
        assertEquals(sloppifyTrack.toString(), underTest.trackInfo(sloppifyTrack.getId()));
    }
}
