package mk.finki.ukim.mk.lab.repository;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySongRepo {

    public List<Song> findAll(){
        return DataHolder.songs;
    }
    public Optional<Song> findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(r->r.getTrackId().equals(trackId)).findFirst();
    }

        public Artist addArtistToSong(Artist artist, Song song) {

            Optional<Song> optionalSong = DataHolder.songs.stream()
                    .filter(s -> s.getTrackId().equals(song.getTrackId()))
                    .findFirst();


            if (optionalSong.isPresent()) {
                Song foundSong = optionalSong.get();

                if (!foundSong.getPerformers().contains(artist)) {
                    foundSong.getPerformers().add(artist);
                }
                return artist;
            } else {
                System.out.println("Song not found!");
                return null;
            }
        }
    public Optional<Song> findById(Long id) {
        return DataHolder.songs.stream()
                .filter(song -> song.getId().equals(id))
                .findFirst();
    }

    public Song save(Song song) {
        // Remove old instance if updating
        findById(song.getId()).ifPresent(DataHolder.songs::remove);
        DataHolder.songs.add(song);
        return song;
    }

    public void deleteById(Long id) {
        DataHolder.songs.removeIf(song -> song.getId().equals(id));
    }
    }


