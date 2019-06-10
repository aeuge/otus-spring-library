package ru.otus.library.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("users")
public class LibraryUsers {
        @Id
        public String id;

        public String username;
        public String password;
        public List<String> roles = new ArrayList<>();

        public LibraryUsers(String username, String password, List<String> roles) {
                this.username = username;
                this.password = password;
                this.roles.addAll(roles);
        }
}
