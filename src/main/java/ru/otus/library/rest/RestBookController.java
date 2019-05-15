package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.service.BookService;

@RestController
public class RestBookController {

    private final BookService service;

    @Autowired
    public RestBookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/api/allbooks")
    public Flux<BookDto> getAllBooks() {
        return service.getAll().map(ConvertToDto::toDto);
    }

    @GetMapping("/api/book/{id}")
    public Mono<BookDto> getBook(@PathVariable String id) {
        return service.getById(id).map(ConvertToDto::toDto);
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String id) {
        Mono<Book> book = service.getById(id);
        //return service.deleteBook(book.);
    }

    @PostMapping("/api/book/{id}")
    public String saveBook(@RequestBody BookDto bookDto) {
        Mono<Book> book;
        if (bookDto.getId().equals("new")) {
            book = Mono.just(new Book());
        } else {
            book = service.getById(bookDto.getId());
        }
        book.map(x-> {
            x.setTitle(bookDto.getTitle());
            x.setAuthor(bookDto.getAuthor());
            x.setGenre(bookDto.getGenre());
            x.setComment(bookDto.getComment());
            return x;
        });
        service.saveBook(book);
        return "ok";
    }
}