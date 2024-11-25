package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // GET метод за да прикажеме сите артисти
    @GetMapping("/artist")
    public String listArtists(Model model) {
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        return "artistsList"; // враќање на изгледот (HTML) за артисти
    }

    // POST метод за додавање на артист
    @PostMapping("/artist")
    public String addArtist(@RequestParam("songRadio") String trackId, Model model) {
        model.addAttribute("trackId", trackId);

        if (trackId == null || trackId.isEmpty()) {
            model.addAttribute("errorMessage", "Track ID е задолжително.");
            return "artistsList"; // враќање на истата страница со порака за грешка
        }

        System.out.println("Track ID selected: " + trackId);


        // Ако успее, ја обновуваме листата со артисти
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("artists", artists);
        model.addAttribute("successMessage", "Артист успешно додаден на песната!");

        // По успешното додавање, можеме да ги прикажеме сите артисти
        return "artistsList";
    }
}
