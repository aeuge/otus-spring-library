package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Genre;

@SpringBootTest
@DisplayName("Поиск жанров через JPA")
class GenreDaoJPATest {
    @Autowired
    GenreDaoJPA gd;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Genre genre = gd.findByGenre("Фантастика");
            Assertions.assertEquals(genre.getId(),10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}