package ru.otus.library.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository dao;

    public BookServiceImpl(BookRepository dao) { this.dao = dao; }

    @Override
    public Mono<Book> getByTitleExact(String title) {
        return dao.findByTitle(title);
    }

    @Override
    public Mono<Book> getByAuthorExact(String author) {
        return dao.findByAuthor(author);
    }

    @Override
    public Flux<Book> getByTitle(String title) {
        return dao.findByTitleContaining(title);
    }

    @Override
    public Flux<Book> getByComment(String comment) { return dao.findByCommentRegex(".*" + comment + ".*"); }

    @Override
    public Flux<Book> getByAuthor(String author) { return dao.findByAuthorRegex(".*" + author + ".*"); }

    @Override
    public Flux<Book> getByGenre(String genre) { return dao.findByGenreRegex(".*" + genre + ".*"); }

    @Override
    public Mono<Book> getById(String id) { return dao.findById(id); }

    @Override
    public void saveBook(Mono<Book> book) { dao.save(book); }

    @Override
    public Flux<Book> getAll() { return dao.findAll(); }

    @Override
    public Flux<Genre> getAllGenre() { return dao.findAllGenre(); }

    @Override
    public Flux<Author> getAllAuthor() { return dao.findAllAuthor(); }

    @Override
    public Flux<Comment> getAllComment() { return dao.findAllComment(); }

    @Override
    public void deleteBook(Book book) { dao.delete(book); }

}
