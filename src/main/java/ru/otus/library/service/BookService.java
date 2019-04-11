package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;

public interface BookService {
    List<Book> getByTitle(String title);
    List<Book> getByComment(String comment);
    List<Book> getByAuthor(String author);
    List<Book> getByGenre(String genre);
    Book getById(String id);
    void saveBook(Book book);
    List<Book> getAll();
    List<String> getAllGenre();
    List<String> getAllAuthor();
    List<String> getAllComment();
    Book getByTitleExact(String title);
    Book getByAuthorExact(String author);
}
