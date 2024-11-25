package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Album;

import mk.finki.ukim.mk.lab.repository.InMemoryAlbumRepo;
import mk.finki.ukim.mk.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final InMemoryAlbumRepo albumRepository;

    public AlbumServiceImpl(InMemoryAlbumRepo albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return DataHolder.albums;
    }

    @Override
    public Album findById(Long albumId) {
        return DataHolder.albums.stream().filter(r-> r.getId().equals(albumId)).findFirst() .orElse(null);
    }


}