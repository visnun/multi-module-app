package ru.nunaev.model.server.dao;

import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

public interface BookDao {

    List<Book> getAll();

    Book getById(Integer id);

    void save(Book book);

    public void delete(List<Integer> books);
}
