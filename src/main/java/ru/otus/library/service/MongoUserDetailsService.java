package ru.otus.library.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.LibraryUsers;
import ru.otus.library.domain.Privilege;
import ru.otus.library.repository.LibraryUsersRepository;

import java.util.Arrays;

@Service
public class MongoUserDetailsService implements ReactiveUserDetailsService {
    private LibraryUsersRepository repository;

    public MongoUserDetailsService(LibraryUsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<UserDetails> user = repository.findByUsername(username).
                switchIfEmpty(Mono.just(new LibraryUsers("anonymous","anonymous",Arrays.asList(new Privilege("NONE"))))).
                map(LibraryUsersConverterService::toUser);
        return user;
    }
}
