package ru.nunaev.common.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.nunaev.model.client.Book;


import java.util.List;
import java.util.Set;

@RemoteServiceRelativePath("springGwtServices/ReadingListController")
public interface ReadingListController extends RemoteService {
    List<Book> getAll();

    void save(Book book);

    void delete(List<Integer> selectedBooks);

    Book byId(Integer id);
}
