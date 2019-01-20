package ru.otus.library.dao;

import ru.otus.library.domain.Book;
import java.util.List;

public interface BookDao {
    long count();
    void insert(Book book);
    List<Book> getAll();
    Book findByTitle(String name);
    Book findByID(long id);
}
