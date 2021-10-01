package ru.nunaev.book.client.activity.booktable;

import ru.nunaev.model.client.Book;

public interface AbstractBookTableActivity {
    void onAddClick();

    void onEditClick(int id);

    void onDeleteClick();
}
