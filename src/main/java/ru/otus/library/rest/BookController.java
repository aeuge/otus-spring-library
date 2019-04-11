package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/book")
    public String editBook(@RequestParam("id") String id, Model model) {
        Book book;
        if (id.equals("new")) {
            book = new Book();
            book.setId("new");
        } else {
            book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        }
        model.addAttribute("book", book);
        return "book";
    }

    @PostMapping("/book/delete")
    public String deleteBook(@RequestParam("id") String id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        bookRepository.delete(book);
        return "redirect:/";
    }

    @PostMapping("/book")
    public String saveBook(@RequestParam("id") String id, @RequestParam("title") String title,  @RequestParam("author") String author, @RequestParam("genre") String genre, @RequestParam("comment") String comment,Model model) {
        Book book;
        if (id.equals("new")) {
            book = new Book();
        } else {
            book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        }
        book.setTitle(title);
        book.setAuthor(Arrays.asList(author));
        book.setGenre(Arrays.asList(genre));
        book.setComment(Arrays.asList(comment));
        bookRepository.save(book);
        model.addAttribute("book", book);
        return "book";
    }
}
