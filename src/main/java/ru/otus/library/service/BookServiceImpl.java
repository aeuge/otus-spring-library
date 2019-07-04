package ru.otus.library.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.Main;
import ru.otus.library.channel.BookAnalizer;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
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
    public Mono<Book> saveBook(Book book) { return dao.save(book); }

    @Override
    public Flux<Book> getAll() {
        Flux<Book> find = dao.findAll();
        List<Book> books = new ArrayList<>();
        //find.subscribe(System.out::println);
        //books = find.map(b->{return books.add(b)});
        find.subscribe(books::add);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        books.forEach(System.out::println);

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);

        BookAnalizer bookAnalizer = ctx.getBean(BookAnalizer.class);

        bookAnalizer.processBook(books);
        return find;
    }

    @Override
    public Flux<Genre> getAllGenre() { return dao.findAllGenre(); }

    @Override
    public Flux<Author> getAllAuthor() { return dao.findAllAuthor(); }

    @Override
    public Flux<Comment> getAllComment() { return dao.findAllComment(); }

    @Override
    public Mono<Void> deleteBook(String id) { return dao.deleteById(id); }

}
