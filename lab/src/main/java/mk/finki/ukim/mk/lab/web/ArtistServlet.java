package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;


@ServletComponentScan
@WebServlet(name = "ArtistServlet", urlPatterns = "/artistServlet")

public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;

    private final SpringTemplateEngine springTemplateEngine;

    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine) {
        this.artistService = artistService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        List<Artist> artists = artistService.listArtists();
        context.setVariable("artists", artists);


        this.springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("songRadio");

        if (trackId == null || trackId.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Track ID is required.");
            return;
        }

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        List<Artist> artist = artistService.listArtists();
        context.setVariable("trackId", trackId);
        context.setVariable("artist", artist);

        this.springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

}
