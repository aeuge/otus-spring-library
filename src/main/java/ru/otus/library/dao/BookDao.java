package ru.otus.library.dao;

import ru.otus.library.domain.Book;
import java.util.List;

public interface BookDao {
    int count();
    int nextID();
    void insert(Book book);
    List<Book> getAll();
    Book findByTitle(String name);
    Book findByID(int id);
}
