package ru.nunaev.book.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteBookEventHandler extends EventHandler {
    public void onDeleteBook(DeleteBookEvent event);
}