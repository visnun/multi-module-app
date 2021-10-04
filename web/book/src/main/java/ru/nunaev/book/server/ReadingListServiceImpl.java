package ru.nunaev.book.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.nunaev.book.server.util.DefaultDataGenerator;
import ru.nunaev.common.client.ReadingListService;
import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class ReadingListServiceImpl extends RemoteServiceServlet implements
        ReadingListService {

    private List<Book> readingList = DefaultDataGenerator.getReadingList();

    private int lastBookId = 2;

    @Override
    public List<Book> getReadingList() {
        return readingList;
    }

    @Override
    public void save(Book book) {
        if (book.getId() == 0) {
            lastBookId += 1;
            book.setId(lastBookId);
            readingList.add(book);
        } else {
            Book bookForEdit = bookById(book.getId());

            bookForEdit.setTitle(book.getTitle());
            bookForEdit.setAuthor(book.getAuthor());
            bookForEdit.setPages(book.getPages());
            bookForEdit.setLanguage(book.getLanguage());
        }
    }

    @Override
    public void delete(Set<Integer> books) {
        books.forEach(index -> {
            for (int i = 0; i < readingList.size(); i++) {
                if (readingList.get(i).getId() == index) {
                    readingList.remove(i);
                }
            }
        });
    }

    @Override
    public Book bookById(Integer id) {
        Book book = null;
        for (Book item : readingList) {
            if (item.getId() == id) {
                book = item;
            }
        }
        return book;
    }
}
