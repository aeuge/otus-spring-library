package ru.otus.library.service;

import com.google.common.collect.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.otus.library.domain.Users;

import java.util.List;

public class ConvertUsersToUser {
    public static User toUser(Users users) {
        List<SimpleGrantedAuthority> authories = Lists.transform(users.getRoles(),(a)->{return new SimpleGrantedAuthority(a);});
        return new User(users.getUsername(), users.getPassword(), authories);
    }
}
