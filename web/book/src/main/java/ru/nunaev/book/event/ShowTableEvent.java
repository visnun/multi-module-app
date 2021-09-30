package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class ShowTableEvent extends GwtEvent<ShowTableEventHandler> {
    public static Type<ShowTableEventHandler> TYPE = new Type<>();

    private Panel panel;

    @Override
    public Type<ShowTableEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ShowTableEventHandler handler) {
        handler.onShowTable(this);
    }
}
