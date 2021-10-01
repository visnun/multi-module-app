package ru.nunaev.common.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.nunaev.model.client.Book;

import java.util.List;
import java.util.Set;

public interface ReadingListServiceAsync {
    void getReadingList(AsyncCallback<List<Book>> async);

    void save(Book book, AsyncCallback<Void> async);

    void delete(Set<Integer> selectedBooks, AsyncCallback<Void> async);

    void bookById(int id, AsyncCallback<Book> async);
}
