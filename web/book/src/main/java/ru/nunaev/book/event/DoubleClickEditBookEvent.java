package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;

public class DoubleClickEditBookEvent extends GwtEvent<DoubleClickEditBookEventHandler> {
    public static Type<DoubleClickEditBookEventHandler> TYPE = new Type<>();

    @Override
    public Type<DoubleClickEditBookEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DoubleClickEditBookEventHandler handler) {
        handler.onDoubleClickEdit(this);
    }
}
