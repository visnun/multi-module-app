package ru.nunaev.book.client.ui.bookform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import ru.nunaev.book.client.lang.Lang;
import ru.nunaev.book.event.SaveBookEvent;
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;

public class BookForm extends Composite implements AbstractBookForm {
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

    @Inject
    public void init(Lang lang) {
        initWidget(formViewUiBinder.createAndBindUi(this));
        cancelButton.setText(lang.cancel());
        saveButton.setText(lang.save());
        addEventHandlers();
        addClickHandlers();
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

    public void showBookForm() {
        RootPanel.get().clear();
        RootPanel.get().add(this);
    }

    public void addEventHandlers() {
        eventBus.addHandler(ShowBookFormEvent.TYPE, event -> showBookForm());
    }

    public void addClickHandlers() {
        cancelButton.addClickHandler(event -> eventBus.fireEvent(new ShowTableEvent()));

        saveButton.addClickHandler(event -> eventBus.fireEvent(new SaveBookEvent()));
    }

    interface FormViewUiBinder extends UiBinder<HTMLPanel, BookForm> {}
    private static final FormViewUiBinder formViewUiBinder = GWT.create(FormViewUiBinder.class);
}
