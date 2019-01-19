package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.GenreDaoJPA;
import ru.otus.library.domain.Genre;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreDaoJPA dao;

    public GenreServiceImpl(GenreDaoJPA dao) {
        this.dao = dao;
    }

    @Override
    public Genre getByGenre(String genre) {
        return dao.findByGenre(genre);
    }

    @Override
    public Genre getByID(long id) {
        return dao.findByID(id);
    }

    @Override
    public void saveGenre(Genre genre) { dao.insert(genre); }

    @Override
    public List<Genre> getAll() { return dao.getAll(); }

    public void setDao(GenreDaoJPA dao) {
        this.dao = dao;
    }

}
