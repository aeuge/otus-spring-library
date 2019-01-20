package ru.otus.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Genre;

@DataJpaTest
@Import(GenreServiceImpl.class)
@DisplayName("Тестирование DAO жанра")
class GenreServiceImplTest {
    @Autowired
    GenreService genreService;

    @Test
    @DisplayName("успешно пройдено с известным ID")
    void getByName() {
        try {
            Genre genre = new Genre(10, "Комедия");
            genreService.saveGenre(genre);
            Assertions.assertEquals(genreService.getByGenre(genre.getGenre()).getGenre(),genre.getGenre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("успешно пройдено без ID")
    void getByNameNew() {
        try {
            Genre genre = new Genre("Комедия");
            genreService.saveGenre(genre);
            Assertions.assertEquals(genreService.getByGenre(genre.getGenre()).getGenre(),genre.getGenre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}