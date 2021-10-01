package ru.nunaev.book.client.view.bookform;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import ru.nunaev.book.client.activity.bookform.AbstractBookFormActivity;
import ru.nunaev.book.client.activity.bookform.AbstractBookFormView;
import ru.nunaev.book.client.lang.Lang;

public class BookFormView extends Composite implements AbstractBookFormView {
    @Inject
    public void init() {
        initWidget(formViewUiBinder.createAndBindUi(this));
        cancelButton.setText(lang.cancel());
        saveButton.setText(lang.save());
    }

    @Override
    public void setActivity(AbstractBookFormActivity activity) {
        this.activity = activity;
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

    @UiHandler("cancelButton")
    void handleCancelButtonClick(ClickEvent event) {
        activity.onCancelClick();
    }

    @UiHandler("saveButton")
    void handleSaveButtonClick(ClickEvent event) {
        activity.onSaveClick();
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

    @UiField
    @Inject
    Lang lang;

    private AbstractBookFormActivity activity;

    interface FormViewUiBinder extends UiBinder<HTMLPanel, BookFormView> {}
    private static final FormViewUiBinder formViewUiBinder = GWT.create(FormViewUiBinder.class);
}
