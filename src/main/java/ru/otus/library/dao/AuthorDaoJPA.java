package ru.otus.library.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Author;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorDaoJPA implements AuthorDao {
    @PersistenceContext
    private EntityManager em;

    public AuthorDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Author findByFIO(String fio) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.fio = :fio", Author.class);
        query.setParameter("fio", fio);
        return query.getSingleResult();
    }

    @Override
    public Author findByID(long id) { return em.find(Author.class, id); }

    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a");
        return (long)query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }
}

