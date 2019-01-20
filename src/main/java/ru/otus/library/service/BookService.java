package ru.otus.library.service;

import ru.otus.library.domain.Book;

import java.util.List;

public interface BookService {
    Book getByTitle(String title);
    Book getById(long id);
    void saveBook(Book book);
    List<Book> getAll();
}
