package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.CommentDaoJPA;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentDaoJPA dao;

    public CommentServiceImpl(CommentDaoJPA dao) {
        this.dao = dao;
    }

    @Override
    public Comment getByComment(String text) {
        return dao.findByComment(text);
    }

    @Override
    public Comment getByID(long id) {
        return dao.findByID(id);
    }

    @Override
    public void saveComment(Book book) { dao.insert(book); }

    public void setDao(CommentDaoJPA dao) {
        this.dao = dao;
    }

    @Override
    public List<Comment> getAll() { return dao.getAll(); }
}
