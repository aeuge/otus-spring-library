package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.config.YamlProps;
import ru.otus.library.domain.Genre;

@JdbcTest
@Import(GenreDaoJDBC.class)
@EnableConfigurationProperties(YamlProps.class)
@DisplayName("Поиск жанров через JDBC")
class GenreDaoJDBCTest {
    @Autowired
    GenreDaoJDBC gd;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Genre genre = gd.findByGenre("Фантастика");
            Assertions.assertEquals(genre.getId(),1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}