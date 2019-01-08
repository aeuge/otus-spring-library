package ru.otus.library.service;

import ru.otus.library.domain.Author;
import java.util.List;

public interface AuthorService {
    Author getByFIO(String fio);
    Author getByID(long id);
    void saveAuthor(Author author);
    List<Author> getAll();
}
