package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Genre;
import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findBygenreContaining(String genre);
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
}
