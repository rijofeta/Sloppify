package Sloppify.Track;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SloppifyTrackService {

    private final SloppifyTrackRepository sloppifyTrackRepository;

    public void play(String id) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Optional<SloppifyTrack> optionalSloppifyTrack = sloppifyTrackRepository.findById(id);

        if (optionalSloppifyTrack.isEmpty()) {
            throw new IllegalStateException("Track not found");
        }

        SloppifyTrack sloppifyTrack = optionalSloppifyTrack.get();
        File file = new File(sloppifyTrack.getFilePath());
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
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

}

