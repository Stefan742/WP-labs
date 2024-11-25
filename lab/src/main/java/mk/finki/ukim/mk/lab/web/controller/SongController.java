package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Album;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.model.Song;
import mk.finki.ukim.mk.lab.service.AlbumService;
import mk.finki.ukim.mk.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }
    @PostMapping("/artist")
    public String addArtist(@RequestParam(value = "songRadio", required = false) String trackId, Model model) {
        if (trackId == null) {
            model.addAttribute("errorMessage", "Мора да изберете песна.");
            return "songs";
        }

        Song selectedSong = songService.findByTrackId(trackId);
        if (selectedSong == null) {
            model.addAttribute("errorMessage", "Песната не постои.");
            return "songs";
        }

        model.addAttribute("trackId", trackId);
        model.addAttribute("song", selectedSong);
        return "addArtistForm";
    }



    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        List<Song> songs = songService.listSongs();
        model.addAttribute("songs", songs);
        model.addAttribute("error", error);
        return "listSongs";
    }

    @PostMapping("/songs")
    public String saveSong(
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            RedirectAttributes redirectAttributes
    ) {
        Album album = albumService.findAll().stream()
                .filter(a -> a.getId().equals(albumId))
                .findFirst()
                .orElse(null);

        if (album == null) {
            return "redirect:/songs?error=Invalid album";
        }

        songService.saveSong(title, trackId, genre, releaseYear, album);
        redirectAttributes.addFlashAttribute("successMessage", "Song added successfully!");
        return "redirect:/songs";
    }


    @GetMapping("/edit/{trackId}")
    public String editSong(@PathVariable String trackId, Model model) {
        Song song = songService.findByTrackId(trackId);
        System.out.println("trackId: " + trackId);

        if (song == null) {
            return "redirect:/songs?error=Song not found";
        }

        List<Album> albums = albumService.findAll();
        model.addAttribute("song", song);
        model.addAttribute("albums", albums);
        return "add-song";
    }


    @PostMapping("/add-form")
    public String updateSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            RedirectAttributes redirectAttributes
    ) {
        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            redirectAttributes.addFlashAttribute("error", "Pesna ne postoi!");
            return "redirect:/songs/add-form";
        }

        Album album = albumService.findById(albumId);
        if (album == null) {
            redirectAttributes.addFlashAttribute("error", "Nevaliden album!");
            return "redirect:/songs/add-form";
        }

        songService.updateSong(song.getTrackId(), title, trackId, genre, releaseYear, album);
        redirectAttributes.addFlashAttribute("successMessage", "Pesna uspesno azurirana!");
        return "redirect:/songs";
    }


    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        List<Album> albums = albumService.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("song", null);
        return "add-song";
    }


    @DeleteMapping("/songs/delete/{trackId}")
    public String deleteSong(@PathVariable String trackId) {
        songService.deleteSong(trackId);
        return "redirect:/songs";
    }



    @GetMapping("/songs/add-form")
    public String addSongPage(Model model) {
        System.out.println("Entering addSongPage method");

        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song", new Song());
        return "add-song";
    }


}
