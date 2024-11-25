package mk.finki.ukim.mk.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {

    public static List<Artist> artists = new ArrayList<>(5);
    public static List<Song> songs = new ArrayList<>(5);
    public static List<Album> albums = new ArrayList<>(5);


    @PostConstruct
    public void init(){
        artists.add( new Artist(11L,"Phill","Collins", "Phil Collins is an English singer, drummer, and songwriter, known as the frontman of Genesis and for his successful solo career with hits like 'In the Air Tonight' and 'Another Day in Paradise."));
        artists.add(new Artist(12L,"Marija", "Sherifovic", "Popular Serbian singer who won the Eurovision Song Contest in 2007 with her song 'Molitva'."));
        artists.add(new Artist(13L,"Toshe", "Proeski", "Famous Macedonian pop singer and philanthropist, known for his heartfelt songs and helping people."));
        artists.add(new Artist(14L,"Freddie", "Mercury", "Lead vocalist of Queen, known for his powerful vocals."));
        artists.add(new Artist(15L,"Beyonce", "Knowles", "R&B and pop icon, known for her vocal range and dynamic performances."));

        albums.add(new Album(1L, "Disney", "Pop", "1999"));
        albums.add(new Album(2L, "Marija Hitovi", "Pop", "2007"));
        albums.add(new Album(3L, "Toshe Hitovi", "Pop", "2004"));
        albums.add(new Album(4L, "A Night at the Opera", "Rock", "1975"));
        albums.add(new Album(5L, "I Am... Sasha Fierce", "R&B", "2008"));

        songs.add(new Song("1", "You'll Be in My Heart", "Pop", 1999, "https://www.youtube.com/watch?v=git6DCXSqjE", albums.get(0)));
        songs.add(new Song("2", "Molitva", "Pop", 2007, "https://www.youtube.com/watch?v=Kbi08wfT7mA", albums.get(1)));
        songs.add(new Song("3", "Studena", "Pop", 2004, "https://www.youtube.com/watch?v=mKi82Dvffso", albums.get(2)));
        songs.add(new Song("4", "Bohemian Rhapsody", "Rock", 1999, "https://www.youtube.com/watch?v=fJ9rUzIMcZQ", albums.get(3)));
        songs.add(new Song("5", "Halo", "R&B", 2008, "https://www.youtube.com/watch?v=bnVUHWCynig", albums.get(4)));


    }
}
