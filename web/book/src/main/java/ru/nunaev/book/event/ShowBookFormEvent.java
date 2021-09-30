package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Panel;

public class ShowBookFormEvent extends GwtEvent<ShowBookFormEventHandler> {
    public static Type<ShowBookFormEventHandler> TYPE = new Type<>();

    private Panel panel;

    @Override
    public Type<ShowBookFormEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowBookFormEventHandler handler) {
        handler.onShowBookForm(this);
    }
}
