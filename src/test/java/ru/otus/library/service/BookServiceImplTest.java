package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

@DataJpaTest
@Import({BookServiceImpl.class, AuthorServiceImpl.class, GenreServiceImpl.class})
@DisplayName("Тестирование DAO книг")
class BookServiceImplTest {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @Test
    @Transactional
    @DisplayName("должна быть добавлена запись с известным ID и прочитана")
    void getByFIO() {
        Book book = new Book(1,"Конституция",authorService.getById(30), genreService.getById(30));
        bookService.saveBook(book);
        Assertions.assertEquals(bookService.getByTitle(book.getTitle()).get(0).getTitle(),book.getTitle());
    }

    @Test
    @Transactional
    @DisplayName("должна быть добавлена запись без ID и успешно прочитана")
    void getByFIONew() {
        Book book = new Book("Конституция",authorService.getById(30), genreService.getById(30));
        bookService.saveBook(book);
        Assertions.assertEquals(bookService.getByTitle(book.getTitle()).get(0).getTitle(),book.getTitle());
    }

    @Test
    @DisplayName("должно вернуть книгу по части названия")
    @Transactional
    void getByTitlePart() {
        Assertions.assertEquals(bookService.getByTitle("еребр").get(0).getTitle(), "Отзвуки серебряного ветра");
    }
}