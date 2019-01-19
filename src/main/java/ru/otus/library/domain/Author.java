package ru.otus.library.domain;

import javax.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ")
    @SequenceGenerator(name = "AUTHOR_SEQ", sequenceName = "SEQUENCE_AUTHOR", initialValue = 100)
    private long id;
    @Column
    private String fio;

    public Author(){}

    public Author(String fio) {
        this.fio = fio;
    }

    public Author(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
}
