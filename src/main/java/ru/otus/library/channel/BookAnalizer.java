package ru.otus.library.channel;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.library.domain.Book;

import java.util.Collection;

@MessagingGateway
public interface BookAnalizer {

    @Gateway(requestChannel = "booksChannel")
    void processBook(Collection<Book> books);
}
