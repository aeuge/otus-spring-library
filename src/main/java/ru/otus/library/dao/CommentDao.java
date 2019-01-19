package ru.otus.library.dao;

import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import java.util.List;

public interface CommentDao {
    long count();
    void insert(Book book);
    List<Comment> getAll();
    Comment findByComment(String text);
    Comment findByID(long id);
}
