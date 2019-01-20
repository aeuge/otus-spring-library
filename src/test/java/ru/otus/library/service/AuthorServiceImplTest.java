package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;

@DataJpaTest
@Import(AuthorServiceImpl.class)
@DisplayName("Тестирование DAO автора")
class AuthorServiceImplTest {
    @Autowired
    AuthorService authorService;

    @Test
    @DisplayName("успешно пройдено с известным ID")
    @Transactional
    void getByFIO() {
        try {
            Author author = new Author(1,"Лермонтов Михаил Юрьевич");
            authorService.saveAuthor(author);
            Assertions.assertEquals(authorService.getByFio(author.getFio()).getFio(),author.getFio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("успешно пройдено без ID")
    @Transactional
    void getByFIONew() {
        try {
            Author author = new Author("Лермонтов Михаил Юрьевич");
            authorService.saveAuthor(author);
            Assertions.assertEquals(authorService.getByFio(author.getFio()).getFio(),author.getFio());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}