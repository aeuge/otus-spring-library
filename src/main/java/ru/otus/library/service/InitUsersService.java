package ru.otus.library.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.LibraryUsers;
import ru.otus.library.domain.Privilege;
import ru.otus.library.repository.PrivilegeRepository;
import ru.otus.library.repository.UsersRepository;

import java.util.List;

@Service
public class InitUsersService {
    private UsersRepository usersRepository;
    private PrivilegeRepository privilegeRepository;

    public InitUsersService(UsersRepository usersRepository, PrivilegeRepository privilegeRepository) {
        this.usersRepository = usersRepository;
        this.privilegeRepository = privilegeRepository;
        initPrivilegesAndUSers();
    }

    public void initPrivilegesAndUSers() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String newpassword = passwordEncoder.encode("admin");
        Privilege privilege = new Privilege("1", "ROLE_ADMIN");
        Privilege privilege2 = new Privilege("2", "ROLE_USER");
        LibraryUsers user = new LibraryUsers("admin", newpassword, List.of(privilege));
        privilegeRepository.save(privilege);
        privilegeRepository.save(privilege2);
        usersRepository.save(user);
    }
}
