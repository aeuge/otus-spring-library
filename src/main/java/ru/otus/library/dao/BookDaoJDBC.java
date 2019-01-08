package ru.otus.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BookDaoJDBC implements BookDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private AuthorDao authorDao;

    public BookDaoJDBC(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Book findByTitle(String title) { return namedParameterJdbcTemplate.getJdbcOperations().queryForObject("select books.id, books.title, books.fk_authors, books.fk_genres, authors.fio, " +
            "genres.genre from books left outer join authors on books.fk_authors=authors.id left outer join genres on books.fk_genres=genres.id where title = ?", new Object[] {title}, new BookDaoJDBC.BookMapper()); }

    @Override
    public Book findByID(long id) { return namedParameterJdbcTemplate.getJdbcOperations().queryForObject("select books.id, books.title, books.fk_authors, books.fk_genres, authors.fio, " +
            "genres.genre from books left outer join authors on books.fk_authors=authors.id left outer join genres on books.fk_genres=genres.id where id = ?", new Object[] {id}, new BookDaoJDBC.BookMapper()); }

    @Override
    public int count() {
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void insert(Book book) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("title", book.getTitle());
        ((MapSqlParameterSource) namedParameters).addValue("fk_authors",book.getAuthor().getId());
        ((MapSqlParameterSource) namedParameters).addValue("fk_genres",book.getGenre().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update("insert into books (title,fk_genres,fk_authors) values (:title,:fk_genres,:fk_authors)", namedParameters,keyHolder);
        book.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcTemplate.getJdbcOperations().query("select books.id, books.title, books.fk_authors, books.fk_genres, authors.fio, " +
                "genres.genre from books left outer join authors on books.fk_authors=authors.id left outer join genres on books.fk_genres=genres.id",  new BookDaoJDBC.BookMapper());
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author(resultSet.getLong("fk_authors"),resultSet.getString("authors.fio"));
            Genre genre = new Genre(resultSet.getLong("fk_genres"),resultSet.getString("genres.genre"));
            return new Book(resultSet.getLong("id"), resultSet.getString("title"),author,genre);
        }
    }
}

