package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.library.rest.ConvertToDto.toDto;

@RestController
public class RestBookController {

    private final BookRepository repository;

    @Autowired
    public RestBookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/allbooks")
    public List<BookDto> getAllBooks() {
        return repository.findAll().stream().map(ConvertToDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/book/{id}")
    public BookDto getBook(@PathVariable String id) {
        BookDto bookDto;
        if (id.equals("new")) {
            bookDto = new BookDto();
            bookDto.setId("new");
        } else {
            bookDto = toDto(repository.findById(id).orElseThrow(NotFoundException::new));
        }
        return bookDto;
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String id) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(book);
    }

    @PostMapping("/api/book/{id}")
    public String saveBook(@RequestBody BookDto bookDto) {
        Book book;
        if (bookDto.getId().equals("new")) {
            book = new Book();
        } else {
            book = repository.findById(bookDto.getId()).orElseThrow(NotFoundException::new);
        }
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setComment(bookDto.getComment());
        repository.save(book);
        return "ok";
    }
}