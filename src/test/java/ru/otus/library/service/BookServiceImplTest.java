package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

@DataMongoTest
@Import({BookServiceImpl.class})
@DisplayName("Тестирование DAO книг")
class BookServiceImplTest {
    @Autowired
    BookService bookService;

    @BeforeEach
    void before() {
        Book book = new Book("Отзвуки серебряного ветра","Эльтеррус Иар", "Фантастика");
        bookService.saveBook(book);
    }

    @Test
    @DisplayName("должно вернуть книгу по части названия")
    void getByTitlePart() {
        Assertions.assertEquals(bookService.getByTitle("еребр").get(0).getTitle(), "Отзвуки серебряного ветра");
    }

    @Test
    @DisplayName("должна быть добавлена запись без ID и успешно прочитана")
    void getByFIONew() {
        Book book = new Book("Конституция","Народ России", "Сборник правил");
        bookService.saveBook(book);
        Assertions.assertEquals(bookService.getByTitle(book.getTitle()).get(0).getTitle(),book.getTitle());
    }
}