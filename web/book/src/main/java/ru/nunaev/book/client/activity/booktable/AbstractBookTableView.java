package ru.nunaev.book.client.activity.booktable;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.IsWidget;
import ru.nunaev.model.client.Book;

import java.util.Set;

public interface AbstractBookTableView extends IsWidget {

    CellTable<Book> getTable();

    Set<Integer> getSelectedBooks();

    void setActivity(AbstractBookTableActivity activity);

}
