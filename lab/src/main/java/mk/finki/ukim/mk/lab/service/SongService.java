package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Song findById(Long id);

    Song saveSong(String title, String trackId, String genre, int releaseYear, Album album);

    Song updateSong(String id, String title, String trackId, String genre, int releaseYear, Album album);

    void deleteSong(String id);

}