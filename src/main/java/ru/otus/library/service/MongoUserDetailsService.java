package ru.otus.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Users;
import ru.otus.library.repository.UsersRepository;

import java.util.Arrays;

@Component
public class MongoUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private UsersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Mono<UserDetails> findByUsername(String username) {
        Mono<User> user = repository.findByUsername(username).switchIfEmpty(Mono.just(new Users("anonymous","anonymous",Arrays.asList("NONE")))).map(ConvertUsersToUser::toUser);
        return user;
    }
}
