package ru.nunaev.book.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nunaev.book.server.util.DefaultDataGenerator;
import ru.nunaev.common.client.ReadingListService;
import ru.nunaev.model.client.Book;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Service("ReadingListService")
@SuppressWarnings("serial")
public class ReadingListServiceImpl  extends RemoteServiceServlet  implements ReadingListService {

    private final List<Book> readingList = DefaultDataGenerator.getReadingList();

    @Autowired
    private BookServiceImpl bookService;

    @PostConstruct
    public void onCostruct(){
        System.out.println("ReadingListServiceImpl constructed");
    }

    @Override
    public List<Book> getReadingList() {
        return readingList;
    }

    @Override
    public void save(Book book) {
        Integer id = bookService.save(book);
        book.setId(id);
        readingList.add(book);
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
        Book book = null;
        for (Book item : readingList) {
            if (item.getId().equals(id)) {
                book = item;
            }
        }
        return book;
    }
}
