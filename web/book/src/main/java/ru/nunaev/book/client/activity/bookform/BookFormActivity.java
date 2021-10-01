package ru.nunaev.book.client.activity.bookform;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import ru.brainworm.factory.generator.activity.client.activity.Activity;
import ru.nunaev.book.event.SaveBookEvent;
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowEditBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

public abstract class BookFormActivity implements AbstractBookFormActivity, Activity {
    @Inject
    public void init() {
        view.setActivity(this);
        addEventHandlers();
    }

    @Override
    public void onCancelClick() {
        eventBus.fireEvent(new ShowTableEvent());
    }

    @Override
    public void onSaveClick() {
        eventBus.fireEvent(new SaveBookEvent());
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

    private void showEditBookForm(int id) {
        readingListService.bookById(id, new AsyncCallback<Book>() {
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

        readingListService.save(book, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка сохранения");
            }

            @Override
            public void onSuccess(Void result) {
                eventBus.fireEvent(new ShowTableEvent());
            }
        });
    }

    private void addEventHandlers() {
        eventBus.addHandler(ShowBookFormEvent.TYPE, event -> showAddBookForm());

        eventBus.addHandler(ShowEditBookFormEvent.TYPE, event -> showEditBookForm(event.getBookId()));

        eventBus.addHandler(SaveBookEvent.TYPE, event -> saveBook(book));
    }

    @Inject
    AbstractBookFormView view;

    @Inject
    ReadingListServiceAsync readingListService;

    @Inject
    EventBus eventBus;

    private Book book;
}
