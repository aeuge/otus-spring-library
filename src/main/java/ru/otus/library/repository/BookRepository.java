package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
    List<Book> findByCommentRegex(String comment);
    List<Book> findByAuthorRegex(String author);
    List<Book> findByGenreRegex(String genre);
    List<Book> findAll();
    @Query("{},{genre:1}")
    List<Genre> findAllGenre();
    @Query("{},{author:1}")
    List<Author> findAllAuthor();
    @Query("{},{comment:1}")
    List<Comment> findAllComment();
    Optional<Book> findById(Long id);
}
