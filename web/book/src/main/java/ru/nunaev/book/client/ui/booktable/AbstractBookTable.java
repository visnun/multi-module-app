package ru.nunaev.book.client.ui.booktable;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import ru.nunaev.model.client.Book;

import java.util.Set;

public interface AbstractBookTable extends IsWidget {

    CellTable<Book> getTable();

    Button getCreateButton();

    Set<Integer> getSelectedBooks();

    Button getDeleteButton();

}
