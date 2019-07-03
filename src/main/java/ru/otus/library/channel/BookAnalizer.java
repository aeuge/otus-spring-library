package ru.otus.library.channel;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.library.domain.Book;

import java.util.Collection;

@MessagingGateway
public interface BookAnalizer {

    @Gateway(requestChannel = "bookChannel")
    Book process(Collection<Book> books);

    @Gateway(requestChannel = "interestingBookChannel")
    Book process2(Book book);
}
