package ru.nunaev.book.client.activity.booktable;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import ru.brainworm.factory.generator.activity.client.activity.Activity;
import ru.nunaev.book.event.DeleteBookEvent;
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowEditBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

import java.util.List;

public abstract class BookTableActivity implements AbstractBookTableActivity, Activity {
    @Inject
    public void init() {
        view.setActivity(this);
        addEventHandlers();
    }

    @Override
    public void onEditClick(int id) {
        eventBus.fireEvent(new ShowEditBookFormEvent(id));
    }

    @Override
    public void onAddClick() {
        eventBus.fireEvent(new ShowBookFormEvent());
    }

    @Override
    public void onDeleteClick() {
        eventBus.fireEvent(new DeleteBookEvent());
    }

    private void showTable() {
        readingListService.getReadingList(new AsyncCallback<List<Book>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Book> result) {
                view.getTable().setRowCount(result.size(), true);
                view.getTable().setRowData(0, result);

                RootPanel.get().clear();
                RootPanel.get().add(view);
            }
        });
    }

    public void deleteBooks() {
        readingListService.delete(view.getSelectedBooks(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка при удалении");
            }

            @Override
            public void onSuccess(Void result) {
                eventBus.fireEvent(new ShowTableEvent());
            }
        });
    }

    private void addEventHandlers() {
        eventBus.addHandler(ShowTableEvent.TYPE, event -> showTable());

        eventBus.addHandler(DeleteBookEvent.TYPE, event -> deleteBooks());
    }

    @Inject
    AbstractBookTableView view;

    @Inject
    ReadingListServiceAsync readingListService;

    @Inject
    EventBus eventBus;
}
