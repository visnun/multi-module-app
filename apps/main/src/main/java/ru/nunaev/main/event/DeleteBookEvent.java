package ru.nunaev.main.event;

import com.google.gwt.event.shared.GwtEvent;

public class DeleteBookEvent extends GwtEvent<DeleteBookEventHandler> {
    public static Type<DeleteBookEventHandler> TYPE = new Type<>();

    @Override
    public Type<DeleteBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteBookEventHandler handler) {
        handler.deleteBook(this);
    }
}
