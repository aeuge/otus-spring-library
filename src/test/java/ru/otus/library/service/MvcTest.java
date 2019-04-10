package ru.otus.library.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;
import ru.otus.library.rest.AuthorController;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class MvcTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void test() throws Exception{
        given(bookRepository.findById("id")).willReturn(java.util.Optional.of(new Book("Honda")));
        mvc.perform(
                get("/book?id=1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Honda"));
    }
}