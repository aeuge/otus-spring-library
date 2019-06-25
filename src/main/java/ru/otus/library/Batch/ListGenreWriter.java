package ru.otus.library.Batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class ListGenreWriter implements ItemWriter<Book> {
        private ItemWriter<Book> _genreWriter;

        public ListGenreWriter(JdbcBatchItemWriter<Book> genreWriter ) {
            _genreWriter = genreWriter;
        }

        @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
        public void write(List<? extends Book> items) throws Exception
        {
            for ( Book item : items ) {
                ArrayList<Book> tempListBook = new ArrayList<Book>();
                for(String key : item.getGenre()) {
                    tempListBook.add(new Book(item.getId(),"","",key,""));
                }
                _genreWriter.write(tempListBook);
            }
        }
}
