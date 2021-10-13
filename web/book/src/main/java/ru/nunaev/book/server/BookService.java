package ru.nunaev.book.server;

import ru.nunaev.model.client.Book;

public interface BookService {
    Integer save(Book book);
}
