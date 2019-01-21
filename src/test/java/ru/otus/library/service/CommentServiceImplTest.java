package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @DisplayName("должна быть добавлена запись с известным ID и успешно прочитана")
    void getByName() {
        Book book = bookService.getById(10);
        Comment comment = new Comment(10, "супер",bookService.getById(10));
        book.addComment(comment);
        commentService.saveComment(book);
        Assertions.assertEquals(commentService.getByComment(comment.getText()).get(0).getText(),comment.getText());
    }

    @Test
    @Transactional
    @DisplayName("должна быть добавлена запись без ID и успешно прочитана")
    void getByNameWID() {
        Book book = bookService.getById(10);
        Comment comment = new Comment( "супер",bookService.getById(10));
        book.addComment(comment);
        commentService.saveComment(book);
        Assertions.assertEquals(commentService.getByComment(comment.getText()).get(0).getText(),comment.getText());
    }

    @Test
    @DisplayName("должно вернуть комментарий по части комментария")
    @Transactional
    void getByCommentPart() {
        Assertions.assertEquals(commentService.getByComment("интерес").get(0).getText(), "Очень интересная книга");
    }
}