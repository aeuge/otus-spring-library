package ru.otus.library.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_SEQ")
    @SequenceGenerator(name = "GENRE_SEQ", sequenceName = "SEQUENCE_GENRE", initialValue = 100)
    private long id;
    @Column
    private String genre;

    public Genre() {}

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }
}
