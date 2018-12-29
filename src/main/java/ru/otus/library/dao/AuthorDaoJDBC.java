package ru.otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorDaoJDBC implements AuthorDao {
    private final JdbcOperations jdbc;

    public AuthorDaoJDBC(JdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public Author findByFIO(String fio) { return jdbc.queryForObject("select * from authors where fio = ?", new Object[] {fio}, new AuthorMapper()); }

    @Override
    public Author findByID(int id) { return jdbc.queryForObject("select * from authors where id = ?", new Object[] {id}, new AuthorMapper()); }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public int nextID() {
        return jdbc.queryForObject("select max(id) from authors", Integer.class) + 1;
    }

    @Override
    public void insert(Author author) {
        if (author.getId()==0) { author.setId(nextID()); }
        jdbc.update("insert into authors (id, `fio`) values (?, ?)", author.getId(), author.getFio());
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors",  new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String fio = resultSet.getString("fio");
            return new Author(id, fio);
        }
    }
}

