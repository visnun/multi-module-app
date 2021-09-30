package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Panel;

public class DeleteBookEvent extends GwtEvent<DeleteBookEventHandler> {
    public static Type<DeleteBookEventHandler> TYPE = new Type<>();

    private Panel panel;

    @Override
    public Type<DeleteBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteBookEventHandler handler) {
        handler.onDeleteBook(this);
    }
}
