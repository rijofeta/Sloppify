package Sloppify.Track;

import Sloppify.Identifier.Identifier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class SloppifyTrack {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "album", nullable = false)
    private String album;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "release_date", nullable = false)
    private String releaseDate;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "cover_art")
    private String coverArt;

    public SloppifyTrack(
            String title,
            String album,
            String artist,
            String releaseDate
    ) {

        this.id = Identifier.createIdentifier();
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "SloppifyTrack{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", filePath='" + filePath + '\'' +
                ", coverArt='" + coverArt + '\'' +
                '}';
    }
}
