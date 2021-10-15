package ru.nunaev.model.server.dao;

import ru.nunaev.model.client.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book getById(Integer id);

    void save(Book book);

    void delete(List<Integer> books);
}
