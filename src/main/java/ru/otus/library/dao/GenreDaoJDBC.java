package ru.otus.library.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
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
    public int nextID() {
        return jdbc.queryForObject("select max(id) from genres", Integer.class) + 1;
    }

    @Override
    public void insert(Genre genre) {
        if (genre.getId()==0) { genre.setId(nextID()); }
        jdbc.update("insert into genres (id, `genre`) values (?, ?)", genre.getId(), genre.getGenre());
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

