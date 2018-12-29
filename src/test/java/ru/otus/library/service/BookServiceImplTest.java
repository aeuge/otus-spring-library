package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

@SpringBootTest
@DisplayName("Тестирование DAO книг")
class BookServiceImplTest {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("успешно пройдено с известным ID")
    void getByFIO() {
        try {
            Book book = new Book(10,"Конституция",authorService.getByID(3), genreService.getByID(3));
            bookService.saveBook(book);
            Assertions.assertEquals(bookService.getByTitle(book.getTitle()).getTitle(),book.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("успешно пройдено без ID")
    void getByFIONew() {
        try {
            Book book = new Book("Конституция",authorService.getByID(3), genreService.getByID(3));
            bookService.saveBook(book);
            Assertions.assertEquals(bookService.getByTitle(book.getTitle()).getTitle(),book.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}