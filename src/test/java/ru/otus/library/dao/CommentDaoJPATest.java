package ru.otus.library.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.library.domain.Comment;
import ru.otus.library.domain.Genre;

@SpringBootTest
@DisplayName("Поиск комментариев через JPA")
class CommentDaoJPATest {
    @Autowired
    CommentDaoJPA cdao;

    @Test
    @DisplayName("успешно пройдет")
    void findByName() {
        try {
            Comment comment = cdao.findByComment("Очень интересная книга");
            Assertions.assertEquals(comment.getId(),1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}