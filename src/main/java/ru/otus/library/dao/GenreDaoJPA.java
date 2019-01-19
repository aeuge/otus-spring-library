package ru.otus.library.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreDaoJPA implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    public GenreDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Genre findByGenre(String genre) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.genre = :genre", Genre.class);
        query.setParameter("genre", genre);
        return query.getSingleResult();
    }

    @Override
    public Genre findByID(long id) { return em.find(Genre.class, id); }

    @Override
    public long count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return (long)query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }
}

