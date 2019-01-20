package ru.otus.library.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @SequenceGenerator(name = "BOOK_SEQ", sequenceName = "SEQUENCE_BOOK", initialValue = 100)
    private long id;

    @Column
    private String title;
    @OneToOne
    private Author author;
    @OneToOne
    private Genre genre;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    public Book () {}

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }
    public long setId(long id) {
        return this.id=id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void addComment(Comment comment) {
        this.comment.add(comment);
    }

    public Book(long id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author.getFio() +
                ", genre=" + genre.getGenre() +
                ", comments=" + comment +
                '}';
    }
}
