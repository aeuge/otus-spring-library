package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository dao;

    public BookServiceImpl(BookRepository dao) {
        this.dao = dao;
    }

    @Override
    public Book getByTitle(String title) {
        return dao.findBytitleContaining(title);
    }

    @Override
    public Book getById(long id) {
        return dao.findById(id).get();
    }

    @Override
    public void saveBook(Book book) { dao.save(book); }

    public void setDao(BookRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAll() { return dao.findAll(); }
}
