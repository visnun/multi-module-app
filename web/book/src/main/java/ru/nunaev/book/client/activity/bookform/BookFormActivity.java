package ru.nunaev.book.client.activity.bookform;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import ru.brainworm.factory.generator.activity.client.activity.Activity;
import ru.brainworm.factory.generator.activity.client.annotations.Event;
import ru.nunaev.common.client.ReadingListControllerAsync;
import ru.nunaev.common.client.events.BookEvents;
import ru.nunaev.model.client.Book;

public abstract class BookFormActivity implements AbstractBookFormActivity, Activity {
    @Inject
    public void init() {
        view.setActivity(this);
    }

    @Event
    public void onEdit(BookEvents.Edit event) {
        showEditBookForm(event.id);
    }

    @Event
    public void onAdd(BookEvents.Create event) {
        showAddBookForm();
    }

    @Override
    public void onCancelClick() {
        fireEvent(new BookEvents.Show());
    }

    @Override
    public void onSaveClick() {
        saveBook(book);
    }

    private void showAddBookForm() {
        book = new Book();
        book.setId(0);

        view.getBookTitle().setText("");
        view.getAuthor().setText("");
        view.getPages().setText("");
        view.getLanguage().setText("");

        RootPanel.get().clear();
        RootPanel.get().add(view);
    }

    private void showEditBookForm(Integer id) {
        readingListController.bookById(id, new AsyncCallback<Book>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Book result) {
                book = result;
                fillEditForm(book);
            }
        });
    }

    private void fillEditForm(Book book) {
        view.getAuthor().setText(book.getAuthor());
        view.getBookTitle().setText(book.getTitle());
        view.getPages().setText(book.getPages());
        view.getLanguage().setText(book.getLanguage());

        RootPanel.get().clear();
        RootPanel.get().add(view);
    }

    private void saveBook(Book book) {
        book.setTitle(view.getBookTitle().getText());
        book.setAuthor(view.getAuthor().getText());
        book.setLanguage(view.getLanguage().getText());
        book.setPages(view.getPages().getText());

        readingListController.save(book, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка сохранения");
            }

            @Override
            public void onSuccess(Void result) {
                fireEvent(new BookEvents.Show());
            }
        });
    }

    @Inject
    AbstractBookFormView view;

    @Inject
    ReadingListControllerAsync readingListController;

    private Book book;
}
