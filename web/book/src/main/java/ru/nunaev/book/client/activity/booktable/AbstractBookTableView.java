package ru.nunaev.book.client.activity.booktable;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import ru.nunaev.book.client.activity.bookform.AbstractBookFormActivity;
import ru.nunaev.model.client.Book;

import java.util.Set;

public interface AbstractBookTableView extends IsWidget {

    CellTable<Book> getTable();

    Button getAddButton();

    Set<Integer> getSelectedBooks();

    Button getDeleteButton();

    void setActivity(AbstractBookTableActivity activity);

}
