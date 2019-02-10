package ru.otus.library;

import org.jline.utils.AttributedString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.shell.jline.PromptProvider;
import ru.otus.library.config.YamlProps;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(YamlProps.class)
public class Main {

    public static void main(String[] args) throws Exception{ SpringApplication.run(Main.class, args); }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("library-shell:>");
    }
}
