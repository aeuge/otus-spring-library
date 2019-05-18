package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import reactor.core.publisher.Flux;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.rest.BookController;
import ru.otus.library.rest.BookDto;
import ru.otus.library.rest.RestBookController;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(secure = false)
@org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
@DisplayName("Тестирование рест контроллера WebFlux")
public class WebFluxTest {
    @Autowired
    private WebTestClient fluxTest;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("должен вернуться список книг")
    public void test() throws Exception{
        Flux<Book> book = Flux.just(new Book("Honda"));
        when(bookService.getAll()).thenReturn(book);

        fluxTest.get().uri("/api/allbooks")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{\"id\":null,\"title\":\"Honda\",\"author\":[],\"genre\":[],\"comment\":[]}]");
    }
}