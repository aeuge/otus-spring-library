package ru.otus.library.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.LibraryUsers;
import ru.otus.library.domain.Privilege;

@Repository
public interface PrivilegeRepository extends ReactiveMongoRepository<Privilege, String> {
    Mono<Privilege> findByName(String name);
}
