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
    @DisplayName("успешно пройдено с известным ID")
    void getByFIO() {
        try {
            Book book = new Book(1,"Конституция",authorService.getById(30), genreService.getById(30));
            bookService.saveBook(book);
            Assertions.assertEquals(bookService.getByTitle(book.getTitle()).getTitle(),book.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @DisplayName("успешно пройдено без ID")
    void getByFIONew() {
        try {
            Book book = new Book("Конституция",authorService.getById(30), genreService.getById(30));
            bookService.saveBook(book);
            Assertions.assertEquals(bookService.getByTitle(book.getTitle()).getTitle(),book.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}