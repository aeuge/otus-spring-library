package ru.otus.library.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;
import ru.otus.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository dao;
    MongoTemplate mongoTemplate;

    public BookServiceImpl(BookRepository dao, MongoTemplate mongoTemplate) {
        this.dao = dao;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> getByTitle(String title) {
        return dao.findByTitleContaining(title);
    }

    @Override
    public List<Book> getByComment(String comment) { return dao.findByCommentRegex(".*" + comment + ".*"); }

    @Override
    public List<Book> getByAuthor(String author) { return dao.findByAuthorRegex(".*" + author + ".*"); }

    @Override
    public List<Book> getByGenre(String genre) { return dao.findByGenreRegex(".*" + genre + ".*"); }

    @Override
    public Book getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveBook(Book book) { dao.save(book); }

    @Override
    public List<Book> getAll() { return dao.findAll(); }

    @Override
    public List<String> getAllGenre() {
        List<Genre> listAuthor = dao.findAllGenre();
        List<String> newListGenre = new ArrayList<>();
        listAuthor.forEach((l)->{l.getGenre().forEach(newListGenre::add);});
        return newListGenre.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getAllAuthor() {
        List<Author> listAuthor = dao.findAllAuthor();
        List<String> newListAuthor = new ArrayList<>();
        listAuthor.forEach((l)->{l.getAuthor().forEach(newListAuthor::add);});
        return newListAuthor.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getAllComment() {
        List<Comment> listComment = dao.findAllComment();
        List<String> newListComment = new ArrayList<>();
        listComment.forEach((l)->{l.getComment().forEach(newListComment::add);});
        return newListComment.stream().distinct().collect(Collectors.toList());
    }

}
