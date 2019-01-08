package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.BookDaoJDBC;
import ru.otus.library.domain.Book;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDaoJDBC dao;

    public BookServiceImpl(BookDaoJDBC dao) {
        this.dao = dao;
    }

    @Override
    public Book getByTitle(String title) {
        return dao.findByTitle(title);
    }

    @Override
    public Book getByID(long id) {
        return dao.findByID(id);
    }

    @Override
    public void saveBook(Book book) { dao.insert(book); }

    public void setDao(BookDaoJDBC dao) {
        this.dao = dao;
    }

    @Override
    public List<Book> getAll() { return dao.getAll(); }
}
