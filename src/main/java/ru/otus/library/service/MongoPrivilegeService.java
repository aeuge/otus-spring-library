package ru.otus.library.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Privilege;
import ru.otus.library.repository.PrivilegeRepository;

@Service
public class MongoPrivilegeService  {
    private PrivilegeRepository repository;

    public MongoPrivilegeService(PrivilegeRepository repository) {
        this.repository = repository;
    }

    public Mono<Privilege> findByPrivilege(Privilege privilege) {
        return repository.findByName(privilege.getName()).switchIfEmpty(Mono.just(new Privilege("NONE")));
    }
}
