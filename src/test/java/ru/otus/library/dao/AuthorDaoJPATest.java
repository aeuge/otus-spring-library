package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Author;

@SpringBootTest
@DisplayName("Поиск авторов через JPA")
class AuthorDaoJPATest {
    @Autowired
    AuthorDaoJPA ad;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Author author = ad.findByFIO("Эльтеррус Иар");
            Assertions.assertEquals(author.getId(),30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}