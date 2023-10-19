package Sloppify.Search;

import Sloppify.Track.SloppifyTrackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {

    private final SloppifyTrackService sloppifyTrackService;

    public String search(String text) {
        return sloppifyTrackService.search(text);
    }
}
