package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.ArtistService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {

    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public String showSongDetails(@RequestParam(required = false) String trackId, Model model) {
        Song song = null;

        // Ако нема trackId, земи го првиот song
        if (trackId != null) {
            song = songService.findByTrackId(trackId);
        } else {
            song = songService.listSongs().stream().findFirst().orElse(null);
        }

        // Додај song во модел
        model.addAttribute("entity", song);

        return "songDetails"; // HTML шаблонот за прикажување детали
    }

    @PostMapping
    public String addArtistToSong(
            @RequestParam(required = false) String trackId,
            @RequestParam(required = false) String artistId,
            Model model) {

        System.out.println("Received artistId: " + artistId);
        System.out.println("Received trackId: " + trackId);

        if (trackId == null) {
            model.addAttribute("errorMessage", "Track ID is missing!");
            return "songDetails"; // Назад со error
        }

        Song song = songService.findByTrackId(trackId);

        if (song == null) {
            model.addAttribute("errorMessage", "Song not found for trackId: " + trackId);
            return "songDetails"; // Назад со error
        }

        if (artistId != null) {
            Artist artist = artistService.findById(Long.valueOf(artistId));

            if (artist != null) {
                song.addPerformer(artist);
                System.out.println("Added performer: " + artist.getFirstName() + " " + artist.getLastName());
            } else {
                model.addAttribute("errorMessage", "Artist not found for ID: " + artistId);
            }
        }

        model.addAttribute("entity", song);

        return "songDetails"; // Назад на истата страница со ажурирани детали
    }
}
