package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Genre;

@DataJpaTest
@Import(GenreServiceImpl.class)
@DisplayName("Тестирование DAO жанра")
class GenreServiceImplTest {
    @Autowired
    GenreService genreService;

    @Test
    @Transactional
    @DisplayName("должна быть добавлена запись с известным ID и прочитана")
    void getByName() {
        Genre genre = new Genre(10, "Комедия");
        genreService.saveGenre(genre);
        Assertions.assertEquals(genreService.getByGenre(genre.getGenre()).get(0).getGenre(),genre.getGenre());
    }

    @Test
    @Transactional
    @DisplayName("должна быть добавлена запись без ID и прочитана")
    void getByNameNew() {
        Genre genre = new Genre("Комедия");
        genreService.saveGenre(genre);
        Assertions.assertEquals(genreService.getByGenre(genre.getGenre()).get(0).getGenre(),genre.getGenre());
    }

    @Test
    @DisplayName("должно вернуть комментарий по части комментария")
    @Transactional
    void getByGenrePart() {
        Assertions.assertEquals(genreService.getByGenre("антаст").get(0).getGenre(), "Фантастика");
    }
}