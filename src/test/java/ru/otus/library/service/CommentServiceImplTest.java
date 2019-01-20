package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;

@DataJpaTest
@Import({CommentServiceImpl.class, BookServiceImpl.class})
@DisplayName("Тестирование DAO комментариев")
class CommentServiceImplTest {
    @Autowired
    CommentService commentService;
    @Autowired
    BookService bookService;

    @Test
    @DisplayName("успешно пройдено с известным ID")
    void getByName() {
        try {
            Book book = bookService.getById(10);
            Comment comment = new Comment(10, "супер",bookService.getById(10));
            book.addComment(comment);
            commentService.saveComment(book);
            Assertions.assertEquals(commentService.getByComment(comment.getText()).getText(),comment.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("успешно пройдено без ID")
    void getByNameWID() {
        try {
            Book book = bookService.getById(10);
            Comment comment = new Comment( "супер2",bookService.getById(10));
            book.addComment(comment);
            commentService.saveComment(book);
            Assertions.assertEquals(commentService.getByComment(comment.getText()).getText(),comment.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}