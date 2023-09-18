package Sloppify.Upload;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "upload")
@AllArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping
    public String upload(
            @RequestParam("title") String title,
            @RequestParam("album") String album,
            @RequestParam("artist") String artist,
            @RequestParam("release_date") String releaseDate,
            @RequestParam("track_file") MultipartFile trackFile,
            @RequestParam("cover_art") MultipartFile coverArt
    ) {
        return uploadService.upload(
                title,
                album,
                artist,
                releaseDate,
                trackFile,
                coverArt
        );
    }

}
