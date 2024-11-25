package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAlbumRepo {

    private final List<Album> albums = new ArrayList<>();

    public InMemoryAlbumRepo() {
        albums.add(new Album(1L, "Rock Legends", "Rock", "1990"));
        albums.add(new Album(2L, "Pop Hits", "Pop", "2005"));
        albums.add(new Album(3L, "Classical Favorites", "Classical", "1980"));
        albums.add(new Album(4L, "Jazz Essentials", "Jazz", "1975"));
        albums.add(new Album(5L, "Electronic Beats", "Electronic", "2015"));
    }

    public List<Album> findAll() {
        return albums;
    }
}
