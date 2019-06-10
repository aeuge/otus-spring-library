package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.service.BookService;

@RestController
public class RestBookController {

    private final BookService service;

    @Autowired
    public RestBookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/api/allbooks")
    public Flux<BookDto> getAllBooks(java.security.Principal principal) {
        System.out.println("Authorities: "+ ((Authentication) principal).getAuthorities());
        return service.getAll().map(ConverterBookToDto::toDto);
    }

    @GetMapping("/api/book/{id}")
    public Mono<BookDto> getBook(@PathVariable String id) {
        return service.getById(id).map(ConverterBookToDto::toDto);
    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBook(@PathVariable String id) {
        return service.deleteBook(id);
    }

    @PostMapping("/api/book/{id}")
    public Mono<Book> saveBook(@RequestBody BookDto bookDto) {
        return service.getById(bookDto.getId()).switchIfEmpty(Mono.just(new Book()))
                .map(v -> {
                    v.setTitle(bookDto.getTitle());
                    v.setAuthor(bookDto.getAuthor());
                    v.setGenre(bookDto.getGenre());
                    v.setComment(bookDto.getComment());
                    return v;
                }).flatMap(service::saveBook);
    }
}