package mk.finki.ukim.mk.lab.service.impl;


import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Artist;
import mk.finki.ukim.mk.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Override
    public List<Artist> listArtists() {
        return DataHolder.artists;
    }

    @Override
    public Artist findById(Long id) {
        return DataHolder.artists.stream()
                .filter(artist -> artist.getId().equals(id))
                .findFirst()
                .orElse(null);    }


}
