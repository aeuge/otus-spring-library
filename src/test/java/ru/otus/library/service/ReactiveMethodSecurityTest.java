package ru.otus.library.service;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.rest.BookController;

import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@WebFluxTest
@DisplayName("Тестирование реактивной безопасности для методов ")
public class ReactiveMethodSecurityTest {
    @Autowired
    WebTestClient client;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookController bookController;

    @WithMockUser(
            username = "user",
            password = "user"
    )
    @Test
    @DisplayName("не должно вернуть книгу пользователю без прав на запись")
    public void testUser() throws Exception{
        Mono<Book> book = Mono.just(new Book("Honda"));
        when(bookService.getById("1")).thenReturn(book);

        client.get()
                .uri("/api/book/1")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
    }
}
