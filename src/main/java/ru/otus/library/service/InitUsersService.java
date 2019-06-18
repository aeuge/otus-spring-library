package ru.otus.library.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.LibraryUsers;
import ru.otus.library.domain.Privilege;
import ru.otus.library.repository.PrivilegeRepository;
import ru.otus.library.repository.LibraryUsersRepository;

import java.util.List;

import static java.lang.Thread.sleep;

@Service
public class InitUsersService {
    private LibraryUsersRepository usersRepository;
    private PrivilegeRepository privilegeRepository;

    public InitUsersService(LibraryUsersRepository usersRepository, PrivilegeRepository privilegeRepository) throws InterruptedException {
        this.usersRepository = usersRepository;
        this.privilegeRepository = privilegeRepository;
        initPrivilegesAndUSers();
    }

    public void initPrivilegesAndUSers() throws InterruptedException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
        String adminpassword = passwordEncoder.encode("admin");
        String userpassword = passwordEncoder.encode("user");
        Privilege privilege = new Privilege("1", "ROLE_ADMIN");
        Privilege privilege2 = new Privilege("2", "ROLE_USER");
        Privilege privilege3 = new Privilege("100", "BOOK_READ_PRIVILEGE");
        Privilege privilege4 = new Privilege("101", "BOOK_WRITE_PRIVILEGE");
        LibraryUsers admin = new LibraryUsers("admin", adminpassword, List.of(privilege, privilege3, privilege4));
        LibraryUsers user = new LibraryUsers("user", userpassword, List.of(privilege2, privilege3));
        privilegeRepository.deleteAll().subscribe();
        privilegeRepository.save(privilege).subscribe();
        privilegeRepository.save(privilege2).subscribe();
        privilegeRepository.save(privilege3).subscribe();
        privilegeRepository.save(privilege4).subscribe();
        usersRepository.deleteAll().subscribe();
        sleep(100);
        usersRepository.save(admin).subscribe();
        usersRepository.save(user).subscribe();
    }
}
