package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.finki.ukim.mk.lab.bootstrap.DataHolder.albums;

@Service
public class SongServiceImpl implements SongService {
    @Override
    public List<Song> listSongs() {
        return DataHolder.songs;
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return null;
    }

    @Override
    public Song findByTrackId(String trackId) {
        return DataHolder.songs.stream().filter(r->r.getTrackId().equals(trackId)).findFirst().orElseThrow(null);
    }
    @Override
    public Song findById(Long id) {
        return DataHolder.songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Song saveSong(String title, String trackId, String genre, int releaseYear, Album album) {
        Song song = new Song(trackId, title, genre, releaseYear, null, albums.get(0));
        song.setAlbum(album);
        song.setId((long) (DataHolder.songs.size() + 1));
        DataHolder.songs.add(song);
        return song;
    }

    @Override
    public Song updateSong(String id, String title, String trackId, String genre, int releaseYear, Album album) {
        Song song = findByTrackId(id);

        if (song != null) {
            song.setTitles(title);
            song.setTrackId(trackId);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            song.setAlbum(album);
            return song;
        } else {
            throw new RuntimeException("Song not found!");
        }
    }

    @Override
    public void deleteSong(String id) {
        DataHolder.songs.removeIf(song -> song.getTrackId().equals(id));
    }
}
