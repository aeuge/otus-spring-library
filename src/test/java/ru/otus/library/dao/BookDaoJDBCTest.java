package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.config.YamlProps;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;

@SpringBootTest
@EnableConfigurationProperties(YamlProps.class)
@DisplayName("Поиск книг через JDBC")
class BookDaoJDBCTest {
    @Autowired
    BookDaoJDBC bd;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Book book = bd.findByTitle("Ночной дозор");
            Assertions.assertEquals(book.getId(),3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}