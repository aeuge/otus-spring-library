package ru.otus.library.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Users;
import ru.otus.library.repository.UsersRepository;

import java.util.List;

@Component
public class MongoUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findByUsername(username).block();
        if(user == null) { throw new UsernameNotFoundException("User not found"); }
        List<SimpleGrantedAuthority> authorities = Lists.transform(user.getRoles(),(a)->{return new SimpleGrantedAuthority(a);});
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
