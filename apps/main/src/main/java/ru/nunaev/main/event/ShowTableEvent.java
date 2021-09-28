package ru.nunaev.main.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowTableEvent extends GwtEvent<ShowTableEventHandler> {
    public static Type<ShowTableEventHandler> TYPE = new Type<>();

    @Override
    public Type<ShowTableEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowTableEventHandler handler) {
        handler.showTable(this);
    }
}
