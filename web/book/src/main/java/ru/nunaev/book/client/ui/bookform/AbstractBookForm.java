package ru.nunaev.book.client.ui.bookform;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public interface AbstractBookForm extends IsWidget {

    Button getCancelButton();

    Button getSaveButton();

    TextBox getBookTitle();

    TextBox getAuthor();

    TextBox getPages();

    TextBox getLanguage();
}
