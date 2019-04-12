package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.rest.BookDto;

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
}