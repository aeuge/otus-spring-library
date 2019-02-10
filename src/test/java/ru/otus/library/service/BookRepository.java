package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Book;

@DataMongoTest
@Import({BookRepository.class})
@DisplayName("Тестирование монго репозитория книг")
class BookRepository {
    private BookRepository bookRepository;

    @BeforeEach
    void before(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        Book book = new Book("Отзвуки серебряного ветра","Эльтеррус Иар", "Фантастика");
        //bookRepository.save(book);
    }

    @Test
    @DisplayName("должно вернуть книгу по части названия")
    void getByTitlePart() {
        Assertions.assertEquals(bookRepository.ggetByTitle("еребр").get(0).getTitle(), "Отзвуки серебряного ветра");
    }

    @Test
    @DisplayName("должна быть добавлена запись без ID и успешно прочитана")
    void getByFIONew() {
        Book book = new Book("Конституция","Народ России", "Сборник правил");
        bookService.saveBook(book);
        Assertions.assertEquals(bookService.getByTitle(book.getTitle()).get(0).getTitle(),book.getTitle());
    }

    @Test
    @DisplayName("должно вернуть книгу по части ФИО автора")
    void getByAuthorPart() {
        Assertions.assertEquals(bookService.getByAuthor("Иар").get(0).getAuthor().get(0), "Эльтеррус Иар");
    }
}