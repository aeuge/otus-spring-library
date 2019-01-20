package ru.otus.library.domain;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEXT_SEQ")
    @SequenceGenerator(name = "TEXT_SEQ", sequenceName = "TEXT_COMMENT", initialValue = 100)
    private long id;
    @Column
    private String text;
    @ManyToOne
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Comment(){}

    public Comment(String text) {
        this.text = text;
    }

    public Comment(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Comment(long id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", text='" + text + '\'' +
                ", book='" + book.getId() + '\'';
    }
}
