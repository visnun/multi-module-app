package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;

public class SaveBookEvent extends GwtEvent<SaveBookEventHandler> {
    public static Type<SaveBookEventHandler> TYPE = new Type<>();

    @Override
    public Type<SaveBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SaveBookEventHandler handler) {
        handler.onSaveBook(this);
    }
}
