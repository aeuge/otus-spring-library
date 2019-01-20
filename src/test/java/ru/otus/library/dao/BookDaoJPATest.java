package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Book;

@SpringBootTest
@DisplayName("Поиск книг через JPA")
@AutoConfigureTestEntityManager
class BookDaoJPATest {
    @Autowired
    BookDaoJPA bd;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Book book = bd.findByTitle("Ночной дозор");
            Assertions.assertEquals(book.getId(),30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}