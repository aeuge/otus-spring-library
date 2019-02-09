package ru.otus.library.shell;

import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.config.YamlProps;
import ru.otus.library.domain.Book;
import ru.otus.library.service.BookService;

import java.util.List;
import java.util.Locale;

@ShellComponent
public class Commands {
    public static Locale LOCAL;

    private BookService bookService;

    public Commands(MessageSource messageSource,YamlProps props,BookService bookService) {
        LOCAL = Locale.forLanguageTag(props.getLocaleset());
        System.out.println(messageSource.getMessage( "list.commands",new String[]{},LOCAL));
        this.bookService = bookService;
    }

    @ShellMethod("Список жанров")
    public String listGenre() {
        return bookService.getAllGenre().toString();
    }

    @ShellMethod("Список авторов")
    public String listAuthor() {
        return bookService.getAllAuthor().toString();
    }

    @ShellMethod("Список книг")
    public String listBook() {
        return bookService.getAll().toString();
    }

    @ShellMethod("Список комментариев")
    public String listComment() {
        return bookService.getAllComment().toString();
    }

    @ShellMethod("Поиск комментариев")
    public String findComment(@ShellOption String comment) { return bookService.getByComment(comment).toString(); }

    @ShellMethod("Поиск книг")
    public String findBook(@ShellOption String title) { return bookService.getByTitle(title).toString(); }

    @ShellMethod("Поиск авторов")
    public String findAuthor(@ShellOption String author) { return bookService.getByAuthor(author).toString(); }

    @ShellMethod("Поиск жанров")
    public String findGenre(@ShellOption String genre) { return bookService.getByGenre(genre).toString(); }

    @ShellMethod("Добавить книгу")
    public String addBook(@ShellOption String title, String author, String genre) {
        bookService.saveBook(new Book(title, author, genre));
        return bookService.getAll().toString();
    }

    @ShellMethod("Добавить комментарий для книги")
    public String addComment(@ShellOption String book, String comment) {
        List<Book> book_to_update = bookService.getByTitle(book);
        book_to_update.forEach((b)->{b.addComment(comment); bookService.saveBook(b);});
        return bookService.getAll().toString();
    }

    @ShellMethod("Добавить автора для книги")
    public String addAuthor(@ShellOption String book, String author) {
        List<Book> book_to_update = bookService.getByTitle(book);
        book_to_update.forEach((b)->{b.addAuthor(author); bookService.saveBook(b);});
        return bookService.getAll().toString();
    }

    @ShellMethod("Добавить жанр для книги")
    public String addGenre(@ShellOption String book, String genre) {
        List<Book> book_to_update = bookService.getByTitle(book);
        book_to_update.forEach((b)->{b.addAuthor(genre); bookService.saveBook(b);});
        return bookService.getAll().toString();
    }

}
