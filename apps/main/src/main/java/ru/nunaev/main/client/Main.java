package ru.nunaev.main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import ru.nunaev.common.client.events.BookEvents;
import ru.nunaev.main.client.factory.WidgetFactory;

public class Main implements EntryPoint {
    private final WidgetFactory widgetFactory = GWT.create(WidgetFactory.class);

    @Override
    public void onModuleLoad() {
        widgetFactory.getBookTableActivity().fireEvent(new BookEvents.Show());
    }
}
