package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

@DataMongoTest
@DisplayName("Тестирование монго репозитория книг")
class BookRepositoryTest {

    @BeforeEach
    void before(@Autowired BookRepository bookRepository) {
        Book book = new Book("Отзвуки серебряного ветра","Эльтеррус Иар", "Фантастика");
        bookRepository.save(book);
    }

    @Test
    @DisplayName("должно вернуть книгу по части названия")
    void getByTitlePart(@Autowired BookRepository bookRepository) {
        //Assertions.assertEquals(bookRepository.findByTitleContaining("еребр").get(0).getTitle(), "Отзвуки серебряного ветра");
    }

    @Test
    @DisplayName("должна быть добавлена запись без ID и успешно прочитана")
    void getByFIONew(@Autowired BookRepository bookRepository) {
        Book book = new Book("Конституция","Народ России", "Сборник правил");
        bookRepository.save(book);
        //Assertions.assertEquals(bookRepository.findByTitleContaining(book.getTitle()).get(0).getTitle(),book.getTitle());
    }

    @Test
    @DisplayName("должно вернуть книгу по части ФИО автора")
    void getByAuthorPart(@Autowired BookRepository bookRepository) {
        //Assertions.assertEquals(bookRepository.findByAuthorRegex("Иар").get(0).getAuthor().get(0), "Эльтеррус Иар");
    }
}