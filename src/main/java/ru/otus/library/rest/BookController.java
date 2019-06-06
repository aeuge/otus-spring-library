package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.library.repository.BookRepository;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String listBook() {
        return "index";
    }

    @GetMapping("/book/{id}")
    public String editBook() {
        return "book";
    }
}
