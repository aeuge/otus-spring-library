package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository dao;

    public AuthorServiceImpl(AuthorRepository dao) {
        this.dao = dao;
    }

    @Override
    public Author getByFio(String fio) { return dao.findByfioContaining(fio); }

    @Override
    public Author getById(long id) { return dao.findById(id).get(); }

    @Override
    public void saveAuthor(Author author) { dao.save(author); }

    public void setDao(AuthorRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Author> getAll() { return dao.findAll(); }
}
