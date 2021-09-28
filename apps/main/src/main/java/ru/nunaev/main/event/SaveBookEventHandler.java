package ru.nunaev.main.event;

import com.google.gwt.event.shared.EventHandler;

public interface SaveBookEventHandler extends EventHandler {
    public void saveBook(SaveBookEvent event);
}
