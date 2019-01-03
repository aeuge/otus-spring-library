package ru.otus.library.domain;

public class Genre {
    private long id=0;
    private String genre;

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
