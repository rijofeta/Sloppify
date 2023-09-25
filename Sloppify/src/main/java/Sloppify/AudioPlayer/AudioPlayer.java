package Sloppify.AudioPlayer;

import org.springframework.stereotype.Component;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Component
public class AudioPlayer {

    private Clip clip;

    public AudioPlayer() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(String path) {
        try {
            if (!clip.isOpen() || clip.isRunning()) {
                clip.close();
                File file = new File(path);
                clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                clip.open(audioInputStream);
            }
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void pause() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }
}
