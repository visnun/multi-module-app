package ru.nunaev.main.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowBookFormEvent extends GwtEvent<ShowBookFormEventHandler> {
    public static Type<ShowBookFormEventHandler> TYPE = new Type<>();

    @Override
    public Type<ShowBookFormEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowBookFormEventHandler handler) {
        handler.showBookForm(this);
    }
}
