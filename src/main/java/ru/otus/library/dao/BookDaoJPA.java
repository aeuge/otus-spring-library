package ru.otus.library.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class BookDaoJPA implements BookDao {
    @PersistenceContext
    private EntityManager em;
    public BookDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult(); }

    @Override
    public Book findByID(long id) { return em.find(Book.class, id); }

    @Override
    public long count() {
        Query query = em.createQuery("select count(b) from Book");
        return (long)query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }
}

