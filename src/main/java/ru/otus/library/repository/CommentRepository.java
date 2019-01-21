package ru.otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Comment;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findBytextContainingIgnoreCase(String text);
    List<Comment> findAll();
    Optional<Comment> findById(Long id);
}
