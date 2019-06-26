package ru.otus.library.Batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class ListCommentWriter implements ItemWriter<Book> {
        private ItemWriter<Book> commentWriter;

        public ListCommentWriter ( JdbcBatchItemWriter<Book> commentWriter ) {
            this.commentWriter = commentWriter;
        }
        
        public void write(List<? extends Book> items) throws Exception
        {
            for ( Book item : items ) {
                ArrayList<Book> tempListBook = new ArrayList<Book>();
                for(String key : item.getComment()) {
                    tempListBook.add(new Book(item.getId(),"","","",key));
                }
                commentWriter.write(tempListBook);
            }
        }
}
