package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Flux<Book> findByTitleContaining(String title);
    Flux<Book> findByCommentRegex(String comment);
    Flux<Book> findByAuthorRegex(String author);
    Flux<Book> findByGenreRegex(String genre);
    Flux<Book> findAll();
    @Query("{},{genre:1}")
    Flux<Genre> findAllGenre();
    @Query("{},{author:1}")
    Flux<Author> findAllAuthor();
    @Query("{},{comment:1}")
    Flux<Comment> findAllComment();
    Mono<Book> findById(String id);
    Mono<Book> findByTitle(String title);
    Mono<Book> findByAuthor(String author);
    Mono<Book> save(Book book);
    Mono<Void> deleteById(String id);
}
