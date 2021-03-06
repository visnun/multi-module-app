package ru.nunaev.book.client.activity.bookform;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;

public interface AbstractBookFormView extends IsWidget {

    TextBox getBookTitle();

    TextBox getAuthor();

    TextBox getPages();

    TextBox getLanguage();

    void setActivity(AbstractBookFormActivity activity);
}
