package Sloppify.Track;

import Sloppify.AudioPlayer.AudioPlayer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SloppifyTrackService {

    private final SloppifyTrackRepository sloppifyTrackRepository;
    private final AudioPlayer audioPlayer;

    private static final int SEARCH_LIMIT = 3;
    private static final String[] SEARCH_FIELDS = new String[]{"title", "album", "artist"};

    public void play(String id) {
        Optional<SloppifyTrack> optionalSloppifyTrack = sloppifyTrackRepository.findById(id);

        if (optionalSloppifyTrack.isEmpty()) {
            throw new IllegalStateException("Track not found");
        }

        SloppifyTrack sloppifyTrack = optionalSloppifyTrack.get();
        audioPlayer.play(sloppifyTrack.getFilePath());
    }

    public void pause() {
        audioPlayer.pause();
    }

    public String save(SloppifyTrack sloppifyTrack) {
        sloppifyTrackRepository.save(sloppifyTrack);
        return "Uploaded track successfully";
    }

    public String trackInfo(String id) {
        Optional<SloppifyTrack> optionalSloppifyTrack = sloppifyTrackRepository.findById(id);

        if (optionalSloppifyTrack.isEmpty()) {
            throw new IllegalStateException("Track not found");
        }

        SloppifyTrack sloppifyTrack = optionalSloppifyTrack.get();
        return sloppifyTrack.toString();
    }

    public String search(String text) {
        List<SloppifyTrack> tracksFound = sloppifyTrackRepository.searchBy(text, SEARCH_LIMIT, SEARCH_FIELDS);
        return tracksFound.toString();
    }
}

