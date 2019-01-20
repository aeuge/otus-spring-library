package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository dao;
    private BookRepository daoBook;

    public CommentServiceImpl(CommentRepository dao, BookRepository bookRepository) {
        this.dao = dao;
        this.daoBook = bookRepository;
    }

    @Override
    public Comment getByComment(String text) {
        return dao.findBytextContainingIgnoreCase(text);
    }

    @Override
    public Comment getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveComment(Book book) { daoBook.save(book); }

    public void setDao(CommentRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Comment> getAll() { return dao.findAll(); }
}
