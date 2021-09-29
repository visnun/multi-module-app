package ru.nunaev.main.client.factory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import ru.nunaev.book.client.ui.bookform.AbstractBookForm;
import ru.nunaev.book.client.ui.booktable.AbstractBookTable;
import ru.nunaev.book.client.injector.BookModule;

@GinModules({BookModule.class})
public interface WidgetFactory extends Ginjector {
//    AbstractBookTable getBookTable();
//    AbstractBookForm getBookForm();
    EventBus getEventBus();
}
