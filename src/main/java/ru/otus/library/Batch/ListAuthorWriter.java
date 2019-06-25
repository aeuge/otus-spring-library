package ru.otus.library.Batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class ListAuthorWriter implements ItemWriter<Book> {
        private ItemWriter<Book> _authorWriter;

        public ListAuthorWriter(JdbcBatchItemWriter<Book> authorWriter ) {
            _authorWriter = authorWriter;
        }

        @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
        public void write(List<? extends Book> items) throws Exception {
            for ( Book item : items ) {
                ArrayList<Book> tempListBook = new ArrayList<Book>();
                for(String key : item.getAuthor()) {
                    tempListBook.add(new Book(item.getId(),"",key,"",""));
                }
                _authorWriter.write(tempListBook);
            }
        }
}
