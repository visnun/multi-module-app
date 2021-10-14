package ru.nunaev.book.server.controller;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.nunaev.book.server.service.book.BookService;
import ru.nunaev.book.server.util.DefaultDataGenerator;
import ru.nunaev.common.client.ReadingListController;
import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

@Controller("ReadingListController")
@SuppressWarnings("serial")
public class ReadingListControllerImpl extends RemoteServiceServlet implements ReadingListController {

    @Override
    public List<Book> getReadingList() {
        return bookService.getAll();
    }

    @Override
    public void save(Book book) {
//        if (book.getId() == 0) {
//            lastBookId += 1;
//            book.setId(lastBookId);
//            readingList.add(book);
//        } else {
//            Book bookForEdit = bookById(book.getId());
//
//            bookForEdit.setTitle(book.getTitle());
//            bookForEdit.setAuthor(book.getAuthor());
//            bookForEdit.setPages(book.getPages());
//            bookForEdit.setLanguage(book.getLanguage());
//        }
    }

    @Override
    public void delete(Set<Integer> books) {
        books.forEach(index -> {
            for (int i = 0; i < readingList.size(); i++) {
                if (readingList.get(i).getId().equals(index)) {
                    readingList.remove(i);
                }
            }
        });
    }

    @Override
    public Book bookById(Integer id) {
        return bookService.getById(id);
    }

    @Autowired
    private BookService bookService;

    private List<Book> readingList = DefaultDataGenerator.getReadingList();

}
