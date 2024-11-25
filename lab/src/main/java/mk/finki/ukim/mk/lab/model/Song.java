package mk.finki.ukim.mk.lab.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Song {
    String trackId;
    String title;
    String genre;
    int releaseYear;
    String songUrl;
    List<Artist> performers;
    private Long id;
    private Album album;

    public Song() {

    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public Song(String trackId, String title, String genre, int releaseYear, String songUrl, Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.songUrl = songUrl;
        performers = new ArrayList<>();

    }
    public void addPerformer(Artist performer) {
        performers.add(performer);
    }
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTitles() {
        return title;
    }

    public void setTitles(String titles) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Artist> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Artist> performers) {
        this.performers = performers;
    }


}
