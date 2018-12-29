package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Author;

@SpringBootTest
@DisplayName("Тестирование DAO автора")
class AuthorServiceImplTest {
    @Autowired
    AuthorService authorService;

    @Test
    @DisplayName("успешно пройдено с известным ID")
    void getByFIO() {
        try {
            Author author = new Author(10,"Лермонтов Михаил Юрьевич");
            authorService.saveAuthor(author);
            Assertions.assertEquals(authorService.getByFIO(author.getFio()).getFio(),author.getFio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("успешно пройдено без ID")
    void getByFIONew() {
        try {
            Author author = new Author("Лермонтов Михаил Юрьевич");
            authorService.saveAuthor(author);
            Assertions.assertEquals(authorService.getByFIO(author.getFio()).getFio(),author.getFio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}