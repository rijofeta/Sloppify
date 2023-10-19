package Sloppify.Search;

import Sloppify.Track.SloppifyTrack;

import java.util.List;


public interface SearchRepository {
    List<SloppifyTrack> searchBy(String text, int limit, String[] fields);
}
