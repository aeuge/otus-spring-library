package ru.otus.library.service;

import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import java.util.List;

public interface CommentService {
    Comment getByComment(String text);
    Comment getById(long id);
    void saveComment(Book book);
    List<Comment> getAll();
}
