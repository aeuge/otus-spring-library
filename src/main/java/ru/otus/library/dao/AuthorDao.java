package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import java.util.List;

public interface AuthorDao {
    int count();
    int nextID();
    void insert(Author author);
    List<Author> getAll();
    Author findByFIO(String fio);
    Author findByID(int id);
}
