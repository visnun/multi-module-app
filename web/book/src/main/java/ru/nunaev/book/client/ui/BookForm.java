package ru.nunaev.book.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

public class BookForm extends Composite {
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

    public BookForm() {
        initWidget(formViewUiBinder.createAndBindUi(this));
        cancelButton.setText("Отмена");
        saveButton.setText("Сохранить");
    }


    public Button getCancelButton() {
        return cancelButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public TextBox getBookTitle() {
        return bookTitle;
    }

    public TextBox getAuthor() {
        return author;
    }

    public TextBox getPages() {
        return pages;
    }

    public TextBox getLanguage() {
        return language;
    }

    interface FormViewUiBinder extends UiBinder<HTMLPanel, BookForm> {}
    private static final FormViewUiBinder formViewUiBinder = GWT.create(FormViewUiBinder.class);
}
