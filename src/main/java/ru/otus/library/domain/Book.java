package ru.otus.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("books")
public class Book {
    @Id
    private String id;
    private String title;
    private List<String> author = new ArrayList<>();
    private List<String> genre = new ArrayList<>();
    private List<String> comment = new ArrayList<>();

    public Book () {}

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author.add(author);
        this.genre.add(genre);
    }

    public Book(String title) {
        this.title = title;
    }

    public void addComment(String comment) {
        this.comment.add(comment);
    }

    public void addGenre(String genre) { this.genre.add(genre); }

    public void addAuthor(String author) { this.author.add(author); }

}
