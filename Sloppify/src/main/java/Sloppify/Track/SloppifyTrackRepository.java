package Sloppify.Track;

import Sloppify.Search.SearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SloppifyTrackRepository extends JpaRepository<SloppifyTrack, String>, SearchRepository {

}
