package ru.otus.library.service;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getByGenre(String genre);
    Genre getById(long id);
    void saveGenre(Genre genre);
    List<Genre> getAll();
}
