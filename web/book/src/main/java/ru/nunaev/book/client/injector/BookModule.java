package ru.nunaev.book.client.injector;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import ru.nunaev.book.client.ui.bookform.AbstractBookForm;
import ru.nunaev.book.client.ui.bookform.BookForm;
import ru.nunaev.book.client.ui.booktable.AbstractBookTable;
import ru.nunaev.book.client.ui.booktable.BookTable;

public class BookModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(AbstractBookForm.class).to(BookForm.class).in(Singleton.class);
        bind(AbstractBookTable.class).to(BookTable.class).in(Singleton.class);
    }
}
