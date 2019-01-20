package ru.otus.library.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class CommentDaoJPA implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    public CommentDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment findByComment(String text) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.text = :text", Comment.class);
        query.setParameter("text", text);
        return query.getSingleResult();
    }

    @Override
    public Comment findByID(long id) { return em.find(Comment.class, id); }

    @Override
    public long count() {
        Query query = em.createQuery("select count(c) from Comment a");
        return (long)query.getSingleResult();
    }

    @Override
    @Transactional
    public void insert(Book book) {
        em.merge(book);
    }

    @Override
    public List<Comment> getAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }
}

