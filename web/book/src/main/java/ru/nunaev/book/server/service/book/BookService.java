package ru.nunaev.book.server.service.book;

import ru.nunaev.model.client.Book;

import java.util.List;

public interface BookService {
    Integer save(Book book);

    List<Book> getAll();

    Book getById(Integer id);


    void delete(List<Integer> books);
}
