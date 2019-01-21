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
    @DisplayName("должна быть добавлена запись с известным ID и прочитана")
    @Transactional
    void getByFIO() {
        Author author = new Author(1,"Лермонтов Михаил Юрьевич");
        authorService.saveAuthor(author);
        Assertions.assertEquals(authorService.getByFio(author.getFio()).get(0).getFio(),author.getFio());
    }

    @Test
    @DisplayName("должна быть добавлена запись без ID и прочитана")
    @Transactional
    void getByFIONew() {
        Author author = new Author("Лермонтов Михаил Юрьевич");
        authorService.saveAuthor(author);
        Assertions.assertEquals(authorService.getByFio(author.getFio()).get(0).getFio(),author.getFio());
    }

    @Test
    @DisplayName("должно вернуть автора по части ФИО")
    @Transactional
    void getByFIOPart() {
        Assertions.assertEquals(authorService.getByFio("Сер").get(0).getFio(), "Пушкин Александр Сергеевич");
    }
}