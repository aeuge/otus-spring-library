package ru.otus.library.domain;

public class Author {
    private long id=0;
    private String fio;

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
