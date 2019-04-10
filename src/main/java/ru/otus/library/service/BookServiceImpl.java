package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository dao;

    public BookServiceImpl(BookRepository dao) { this.dao = dao; }

    @Override
    public Book getByTitleExact(String title) {
        return dao.findByTitle(title);
    }

    @Override
    public Book getByAuthorExact(String author) {
        return dao.findByAuthor(author);
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
    public Book getById(String id) { return dao.findById(id).get(); }

    @Override
    public void saveBook(Book book) { dao.save(book); }

    @Override
    public List<Book> getAll() { return dao.findAll(); }

    @Override
    public List<String> getAllGenre() { return dao.findAllGenre().stream().flatMap(genre -> genre.getGenre().stream()).distinct().collect(Collectors.toList()); }

    @Override
    public List<String> getAllAuthor() { return dao.findAllAuthor().stream().flatMap(author -> author.getAuthor().stream()).distinct().collect(Collectors.toList()); }

    @Override
    public List<String> getAllComment() { return dao.findAllComment().stream().flatMap(comment -> comment.getComment().stream()).distinct().collect(Collectors.toList()); }

}
