package ru.nunaev.common.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.nunaev.model.client.Book;


import java.util.List;
import java.util.Set;

@RemoteServiceRelativePath("list")
public interface ReadingListService extends RemoteService {
    List<Book> getReadingList();

    void save(Book book);

    void delete(Set<Integer> selectedBooks);
}
