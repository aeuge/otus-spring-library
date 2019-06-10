package ru.otus.library.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.otus.library.domain.LibraryUsers;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryUsersConverterService {
    public static User toUser(LibraryUsers users) {
        List<SimpleGrantedAuthority> authorities = users.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());;
        return new User(users.getUsername(), users.getPassword(), authorities);
    }
}
