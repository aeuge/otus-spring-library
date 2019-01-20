package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository dao;

    public GenreServiceImpl(GenreRepository dao) {
        this.dao = dao;
    }

    @Override
    public Genre getByGenre(String genre) {
        return dao.findBygenreContaining(genre);
    }

    @Override
    public Genre getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveGenre(Genre genre) { dao.save(genre); }

    @Override
    public List<Genre> getAll() { return dao.findAll(); }

    public void setDao(GenreRepository dao) {
        this.dao = dao;
    }

}
