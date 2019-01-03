package ru.otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    public void insert(Author author) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        SqlParameterSource namedParameters = new MapSqlParameterSource("fio", author.getFio());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("insert into authors (fio) values (:fio)", namedParameters,keyHolder);
        author.setId(keyHolder.getKey().longValue());
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

