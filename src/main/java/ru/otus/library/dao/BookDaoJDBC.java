package ru.otus.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.GenreService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class BookDaoJDBC implements BookDao {
    private final JdbcOperations jdbc;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorService authorService;

    public BookDaoJDBC(JdbcOperations jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public Book findByTitle(String title) { return jdbc.queryForObject("select * from books where title = ?", new Object[] {title}, new BookDaoJDBC.BookMapper()); }

    @Override
    public Book findByID(int id) { return jdbc.queryForObject("select * from books where id = ?", new Object[] {id}, new BookDaoJDBC.BookMapper()); }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public int nextID() {
        return jdbc.queryForObject("select max(id) from books", Integer.class) + 1;
    }

    @Override
    public void insert(Book book) {
        if (book.getId()==0) { book.setId(nextID()); }
        jdbc.update("insert into books (id, `title`,fk_genres,fk_authors) values (?, ?, ?, ?)", book.getId(), book.getTitle(),book.getAuthor().getId(),book.getGenre().getId());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books",  new BookDaoJDBC.BookMapper());
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            int fk_author = resultSet.getInt("fk_authors");
            Author author = authorService.getByID(fk_author);
            int fk_genre = resultSet.getInt("fk_genres");
            Genre genre = genreService.getByID(fk_genre);
            return new Book(id, title,author,genre);
        }
    }
}

