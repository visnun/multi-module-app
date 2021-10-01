package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;
import ru.nunaev.model.client.Book;

public class ShowEditBookFormEvent extends GwtEvent<ShowEditBookFormEventHandler> {
    public static Type<ShowEditBookFormEventHandler> TYPE = new Type<>();
    
    private int id;

    public ShowEditBookFormEvent(int id) {
        this.id = id;
    }

    public int getBookId() {
        return id;
    }

    @Override
    public Type<ShowEditBookFormEventHandler> getAssociatedType() {
        return TYPE;
    }    
    
    @Override
    protected void dispatch(ShowEditBookFormEventHandler handler) {
        handler.onShowBookEditForm(this);
    }
}
