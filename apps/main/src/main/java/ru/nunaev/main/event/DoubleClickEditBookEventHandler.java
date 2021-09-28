package ru.nunaev.main.event;

import com.google.gwt.event.shared.EventHandler;

public interface DoubleClickEditBookEventHandler extends EventHandler {
    public void showBookForm(DoubleClickEditBookEvent event);

}
