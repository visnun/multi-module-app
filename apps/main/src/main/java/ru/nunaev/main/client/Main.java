package ru.nunaev.main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.main.client.factory.WidgetFactory;

public class Main implements EntryPoint {
    private final WidgetFactory widgetFactory = GWT.create(WidgetFactory.class);

    EventBus eventBus = widgetFactory.getEventBus();

    @Override
    public void onModuleLoad() {
        eventBus.fireEvent(new ShowTableEvent());
    }
}
