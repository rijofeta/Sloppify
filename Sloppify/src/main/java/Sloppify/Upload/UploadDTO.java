package Sloppify.Upload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadDTO {

    private String title;
    private String album;
    private String artist;
    private String releaseDate;

}
