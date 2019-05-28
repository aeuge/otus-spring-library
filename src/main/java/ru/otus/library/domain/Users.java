package ru.otus.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("users")
public class Users {
        @Id
        public String id;

        public String username;
        public String password;
        public List<String> roles;
}
