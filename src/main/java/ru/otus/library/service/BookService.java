package ru.otus.library.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;

public interface BookService {
    Flux<Book> getByTitle(String title);
    Flux<Book> getByComment(String comment);
    Flux<Book> getByAuthor(String author);
    Flux<Book> getByGenre(String genre);
    Mono<Book> getById(String id);
    void saveBook(Mono<Book> book);
    Flux<Book> getAll();
    Flux<Genre> getAllGenre();
    Flux<Author> getAllAuthor();
    Flux<Comment> getAllComment();
    Mono<Book> getByTitleExact(String title);
    Mono<Book> getByAuthorExact(String author);
    void deleteBook(String id);
}
