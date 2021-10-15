package ru.nunaev.book.server.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nunaev.model.client.Book;
import ru.nunaev.model.server.dao.BookDao;

import java.util.List;
import java.util.Set;

@Service
public class  BookServiceImpl implements BookService {

    @Override
    public Integer save(Book book) {
        bookDao.save(book);
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

    @Override
    public void delete(List<Integer> books) {
        bookDao.delete(books);
    }

    @Autowired
    private BookDao bookDao;

}