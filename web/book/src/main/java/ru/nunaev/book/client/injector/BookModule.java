package ru.nunaev.book.client.injector;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import ru.nunaev.book.client.activity.bookform.AbstractBookFormView;
import ru.nunaev.book.client.activity.bookform.BookFormActivity;
import ru.nunaev.book.client.activity.booktable.AbstractBookTableView;
import ru.nunaev.book.client.activity.booktable.BookTableActivity;
import ru.nunaev.book.client.view.bookform.BookFormView;
import ru.nunaev.book.client.view.booktable.BookTableView;
import ru.nunaev.book.server.controller.ReadingListControllerImpl;
import ru.nunaev.common.client.ReadingListController;

public class BookModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(AbstractBookFormView.class).to(BookFormView.class).in(Singleton.class);
        bind(BookFormActivity.class).asEagerSingleton();

        bind(AbstractBookTableView.class).to(BookTableView.class).in(Singleton.class);
        bind(BookTableActivity.class).asEagerSingleton();

        bind(EventBus.class).to(SimpleEventBus.class).asEagerSingleton();

        bind(ReadingListController.class).to(ReadingListControllerImpl.class).in(Singleton.class);
    }
}
