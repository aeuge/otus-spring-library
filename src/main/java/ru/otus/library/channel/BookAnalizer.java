package ru.otus.library.channel;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.library.domain.Book;

import java.util.Collection;

@MessagingGateway
public interface BookAnalizer {

    @Gateway(requestChannel = "booksChannel")
    Book processBook(Collection<Book> books);

    @Gateway(requestChannel = "interestingBookChannel")
    Book processInterestingBook(Book book);

    @Gateway(requestChannel = "nonInterestingBookChannel")
    Book processNonINterestingBook(Book book);
}
