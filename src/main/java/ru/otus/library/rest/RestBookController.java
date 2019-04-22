package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestBookController {

    private final BookRepository repository;

    @Autowired
    public RestBookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllPersons() {
        return repository.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/book/delete")
    public String deleteBook(@RequestBody String bookid, Model model) {
        Book book = repository.findById(bookid).orElseThrow(NotFoundException::new);
        repository.delete(book);
        return "ok";
    }
}