package ru.nunaev.book.client.ui.bookform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import ru.nunaev.book.client.lang.Lang;
import ru.nunaev.book.event.SaveBookEvent;
import ru.nunaev.book.event.ShowEditBookFormEvent;
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

public class BookForm extends Composite implements AbstractBookForm {
    @Inject
    public void init() {
        initWidget(formViewUiBinder.createAndBindUi(this));
        cancelButton.setText(lang.cancel());
        saveButton.setText(lang.save());
        addEventHandlers();
    }

    @Override
    public Button getCancelButton() {
        return cancelButton;
    }

    @Override
    public Button getSaveButton() {
        return saveButton;
    }

    @Override
    public TextBox getBookTitle() {
        return bookTitle;
    }

    @Override
    public TextBox getAuthor() {
        return author;
    }

    @Override
    public TextBox getPages() {
        return pages;
    }

    @Override
    public TextBox getLanguage() {
        return language;
    }

    private void showAddBookForm() {
        book = new Book();
        book.setId(0);

        bookTitle.setText("");
        author.setText("");
        pages.setText("");
        language.setText("");

        RootPanel.get().clear();
        RootPanel.get().add(this);
    }

    private void showEditBookForm(Book book) {
        author.setText(book.getAuthor());
        bookTitle.setText(book.getTitle());
        pages.setText(book.getPages());
        language.setText(book.getLanguage());

        this.book = book;
        RootPanel.get().clear();
        RootPanel.get().add(this);
    }

    private void saveBook(Book book) {
        book.setTitle(bookTitle.getText());
        book.setAuthor(author.getText());
        book.setLanguage(language.getText());
        book.setPages(pages.getText());

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

        eventBus.addHandler(ShowEditBookFormEvent.TYPE, event -> showEditBookForm(event.getBook()));
    }

    @UiHandler("cancelButton")
    void handleCancelButtonClick(ClickEvent event) {
        eventBus.fireEvent(new ShowTableEvent());
    }

    @UiHandler("saveButton")
    void handleSaveButtonClick(ClickEvent event) {
        saveBook(book);
    }

    @UiField
    TextBox bookTitle;

    @UiField
    TextBox author;

    @UiField
    TextBox pages;

    @UiField
    TextBox language;

    @UiField
    Button cancelButton;

    @UiField
    Button saveButton;

    @Inject
    EventBus eventBus;

    @Inject
    ReadingListServiceAsync readingListService;

    @UiField
    @Inject
    Lang lang;

    private Book book;

    interface FormViewUiBinder extends UiBinder<HTMLPanel, BookForm> {}
    private static final FormViewUiBinder formViewUiBinder = GWT.create(FormViewUiBinder.class);
}
