package ru.otus.library.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.config.YamlProps;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.Genre;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;
import java.util.Locale;

@ShellComponent
public class Commands {
    public static Locale LOCAL;

    private GenreService genreService;
    private AuthorService authorService;
    private BookService bookService;

    public Commands(MessageSource messageSource,YamlProps props,BookService bookService,AuthorService authorService,GenreService genreService) {
        LOCAL = Locale.forLanguageTag(props.getLocaleset());
        System.out.println(messageSource.getMessage( "list.commands",new String[]{},LOCAL));
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @ShellMethod("Список жанров")
    public String listGenre() {
        return genreService.getAll().toString();
    }

    @ShellMethod("Список авторов")
    public String listAuthor() {
        return authorService.getAll().toString();
    }


    @ShellMethod("Список книг")
    public String listBook() {
        return bookService.getAll().toString();
    }

    @ShellMethod("Добавить жанр")
    public String addGenre(@ShellOption String genre) {
        genreService.saveGenre(new Genre(genre));
        return genreService.getAll().toString();
    }

    @ShellMethod("Добавить автора")
    public String addAuthor(@ShellOption String fio) {
        authorService.saveAuthor(new Author(fio));
        return authorService.getAll().toString();
    }

    @ShellMethod("Добавить книгу")
    public String addBook(@ShellOption String title, long author, long genre) {
        bookService.saveBook(new Book(title, authorService.getByID(author), genreService.getByID(genre)));
        return bookService.getAll().toString();
    }

}
