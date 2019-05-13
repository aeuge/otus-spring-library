package ru.otus.library.rest;

import lombok.Data;
import ru.otus.library.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.library.rest.ConvertToDto.toDto;

@Data
public class BookDto {
    private String id;
    private String title;
    private List<String> author = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> comment = new ArrayList<>();

    public BookDto() { }

    public BookDto(String title) {
        this.title = title;
    }

    public BookDto(Book book) { toDto(book); }
}
