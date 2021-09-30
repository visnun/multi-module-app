package ru.nunaev.book.event;

import com.google.gwt.event.shared.GwtEvent;
import ru.nunaev.model.client.Book;

public class ShowEditBookFormEvent extends GwtEvent<ShowEditBookFormEventHandler> {
    public static Type<ShowEditBookFormEventHandler> TYPE = new Type<>();
    
    private final Book book;

    public ShowEditBookFormEvent(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
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
