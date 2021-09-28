package ru.nunaev.main.event;

import com.google.gwt.event.shared.EventHandler;

public interface DeleteBookEventHandler extends EventHandler {
    public void deleteBook(DeleteBookEvent event);
}
