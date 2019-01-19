package ru.otus.library.service;

import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDaoJPA;
import ru.otus.library.domain.Author;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorDaoJPA dao;

    public AuthorServiceImpl(AuthorDaoJPA dao) {
        this.dao = dao;
    }

    @Override
    public Author getByFIO(String fio) {
        return dao.findByFIO(fio);
    }

    @Override
    public Author getByID(long id) {
        return dao.findByID(id);
    }

    @Override
    public void saveAuthor(Author author) { dao.insert(author); }

    public void setDao(AuthorDaoJPA dao) {
        this.dao = dao;
    }

    @Override
    public List<Author> getAll() { return dao.getAll(); }
}
