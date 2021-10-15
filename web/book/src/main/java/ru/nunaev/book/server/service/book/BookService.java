package ru.nunaev.book.server.service.book;

import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

public interface BookService {
    Integer save(Book book);

    List<Book> getAll();

    Book getById(Integer id);


    public void delete(List<Integer> books);
}
