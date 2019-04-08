package ru.otus.library.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.library.domain.Book;
import ru.otus.library.repository.BookRepository;

import java.util.List;

@Controller
public class AuthorController {

    private final BookRepository repository;

    @Autowired
    public AuthorController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = repository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String savePage(@RequestParam("id") long id,@RequestParam("name2") String name, Model model) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        book.setTitle(name);
        repository.save(book);
        return listPage(model);
    }
}
