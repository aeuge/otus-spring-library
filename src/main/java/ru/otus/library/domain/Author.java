package ru.otus.library.domain;

public class Author {
    int id=0;
    String fio;

    public Author(String fio) {
        this.fio = fio;
    }

    public Author(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
