package ru.nunaev.book.event;

import com.google.gwt.event.shared.EventHandler;

public interface SaveBookEventHandler extends EventHandler {
    public void onSaveBook(SaveBookEvent event);
}
