package ru.otus.library.channel;

import org.springframework.stereotype.Service;
import ru.otus.library.domain.Book;

@Service
public class BookInterestingService {
    public void isInteresting (Book book) {
        System.out.println("Interesting "+book);
    }

    public void isNonInteresting (Book book) {
        System.out.println("Non Interesting "+book);
    }
}
