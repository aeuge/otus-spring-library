package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.rest.BookController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(secure = false)
@WebMvcTest(BookController.class)
@DisplayName("Тестирование контроллера mvc")
public class MvcTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @DisplayName("должно вернуть книгу")
    public void test() throws Exception{
        Book book = new Book("Honda");
        when(bookRepository.findAll()).thenReturn(book);

        mvc.perform(
                get("/api/allbooks"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"));
    }
}