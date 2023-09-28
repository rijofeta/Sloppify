package Sloppify.Track;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "track")
@AllArgsConstructor
public class SloppifyTrackController {

    private final SloppifyTrackService sloppifyTrackService;

    @GetMapping(path = "{id}")
    public String trackInfo(@PathVariable("id") String id) {
        return sloppifyTrackService.trackInfo(id);
    }

    @GetMapping(path = "{id}/play")
    public void playTrack(@PathVariable("id") String id) {
        sloppifyTrackService.play(id);
    }

    @GetMapping(path = "*/pause")
    public void pauseTrack() {
        sloppifyTrackService.pause();
    }
}
