package ru.nunaev.book.server.controller;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.nunaev.book.server.service.book.BookService;
import ru.nunaev.common.client.ReadingListController;
import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

@Controller("ReadingListController")
@SuppressWarnings("serial")
public class ReadingListControllerImpl extends RemoteServiceServlet implements ReadingListController {

    @Override
    public Book byId(Integer id) {
        return bookService.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @Override
    public void save(Book book) {//
        bookService.save(book);
    }

    @Override
    public void delete(List<Integer> books) {
        bookService.delete(books);
    }

    @Autowired
    private BookService bookService;
}
