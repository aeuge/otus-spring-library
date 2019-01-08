package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import java.util.List;

public interface AuthorDao {
    int count();
    void insert(Author author);
    List<Author> getAll();
    Author findByFIO(String fio);
    Author findByID(long id);
}
