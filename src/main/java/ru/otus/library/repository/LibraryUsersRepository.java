package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.*;

@Repository
public interface LibraryUsersRepository extends ReactiveMongoRepository<LibraryUsers, String> {
    Mono<LibraryUsers> findByUsername(String username);
}
