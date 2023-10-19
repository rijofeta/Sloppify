package Sloppify.Track;

import Sloppify.Identifier.Identifier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.text.SimpleDateFormat;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Indexed
public class SloppifyTrack {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false)
    @FullTextField
    private String title;

    @Column(name = "album", nullable = false)
    @FullTextField
    private String album;

    @Column(name = "artist", nullable = false)
    @FullTextField
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
        return "id: " + id + '\n' +
                "Title: " + title + '\n' +
                "Album: " + album + '\n' +
                "Artist: " + artist + '\n' +
                "Release Date: " + releaseDate;
    }
}
