package ru.otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class GenreDaoJDBC implements GenreDao {
    private final JdbcOperations jdbc;

    public GenreDaoJDBC(JdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public Genre findByGenre(String genre) { return jdbc.queryForObject("select * from genres where genre = ?", new Object[] {genre}, new GenreMapper()); }

    @Override
    public Genre findByID(int id) { return jdbc.queryForObject("select * from genres where id = ?", new Object[] {id}, new GenreMapper()); }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void insert(Genre genre) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
        SqlParameterSource namedParameters = new MapSqlParameterSource("genre", genre.getGenre());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("insert into genres (genre) values (:genre)", namedParameters,keyHolder);
        genre.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genres", new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String genre = resultSet.getString("genre");
            return new Genre(id, genre);
        }
    }
}

