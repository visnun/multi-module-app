package ru.nunaev.book.client.activity.booktable;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import ru.brainworm.factory.generator.activity.client.activity.Activity;
import ru.brainworm.factory.generator.activity.client.annotations.Event;
import ru.brainworm.factory.generator.activity.client.enums.Type;
import ru.nunaev.common.client.events.BookEvents;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

import java.util.List;

public abstract class BookTableActivity implements AbstractBookTableActivity, Activity {
    @Inject
    public void init() {
        view.setActivity(this);
    }

    @Event(Type.FILL_CONTENT)
    public void onShow(BookEvents.Show event) {
        showTable();
    }

    @Override
    public void onEditClick(Integer id) {
        fireEvent(new BookEvents.Edit(id));
    }

    @Override
    public void onAddClick() {
        fireEvent(new BookEvents.Create());
    }

    @Override
    public void onDeleteClick() {
        deleteBooks();
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
                showTable();
            }
        });
    }

    @Inject
    AbstractBookTableView view;

    @Inject
    ReadingListServiceAsync readingListService;
}
