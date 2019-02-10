package ru.otus.library.domain;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private List<String> comment;
}
