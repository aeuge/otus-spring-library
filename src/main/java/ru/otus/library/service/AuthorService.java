package ru.otus.library.service;

import ru.otus.library.domain.Author;
import java.util.List;

public interface AuthorService {
    Author getByFio(String fio);
    Author getById(long id);
    void saveAuthor(Author author);
    List<Author> getAll();
}
