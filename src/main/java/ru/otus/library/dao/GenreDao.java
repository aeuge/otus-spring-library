package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();
    void insert(Genre genre);
    List<Genre> getAll();
    Genre findByGenre(String genre);
    Genre findByID(long id);
}
