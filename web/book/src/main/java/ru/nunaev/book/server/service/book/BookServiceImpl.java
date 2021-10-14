package ru.nunaev.book.server.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nunaev.model.client.Book;
import ru.nunaev.model.server.dao.BookDao;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Integer save(Book book) {

        return book.getId();
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.getById(id);
    }

    @Autowired
    private BookDao bookDao;

}