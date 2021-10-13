package ru.nunaev.book.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nunaev.book.server.util.DefaultDataGenerator;
import ru.nunaev.model.client.Book;

import java.io.Serializable;
import java.util.List;

@Service
public class BookServiceImpl implements Serializable, BookService {
    private int lastBookId = 2;

    private final List<Book> readingList = DefaultDataGenerator.getReadingList();

    @Override
    public Integer save(Book book) {
        if (book.getId() == 0) {
            lastBookId += 1;
            book.setId(lastBookId);
            readingList.add(book);
        }
        return book.getId();
    }
}