package ru.nunaev.book.client.view.booktable;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import ru.nunaev.book.client.activity.booktable.AbstractBookTableActivity;
import ru.nunaev.book.client.activity.booktable.AbstractBookTableView;
import ru.nunaev.book.client.lang.Lang;
import ru.nunaev.model.client.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookTableView extends Composite implements AbstractBookTableView {
    @Inject
    public void init() {
        initWidget(tableUiBinder.createAndBindUi(this));
        initTable(lang);
        initButtons(lang);
        addButtonsHandlers();
    }

    @Override
    public void setActivity(AbstractBookTableActivity activity) {
        this.activity = activity;
    }

    @Override
    public CellTable<Book> getTable() {
        return table;
    }

    @Override
    public List<Integer> getSelectedBooks() {
        return checkedBooks;
    }

    private void initTable(Lang lang) {
        Column<Book, Boolean> checkColumn = new Column<Book, Boolean>(new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(Book object) {
                return null;
            }
        };
        checkColumn.setFieldUpdater((index, book, checked) -> this.changeSelection(book, checked));
        table.addColumn(checkColumn);

        TextColumn<Book> titleColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getTitle();
            }
        };
        table.addColumn(titleColumn, lang.bookTitle());

        TextColumn<Book> authorColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getAuthor();
            }
        };
        table.addColumn(authorColumn, lang.author());

        TextColumn<Book> pagesColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getPages();
            }
        };
        table.addColumn(pagesColumn, lang.pages());

        TextColumn<Book> languageColumn = new TextColumn<Book>() {
            @Override
            public String getValue(Book book) {
                return book.getLanguage();
            }
        };
        table.addColumn(languageColumn, lang.language());

        ButtonCell editButtonCell = new ButtonCell();
        editButtonColumn = new Column<Book, String>(editButtonCell) {
            @Override
            public String getValue(Book object) {
                return BookTableView.this.lang.edit();
            }
        };
        table.addColumn(editButtonColumn, "");
    }

    private void initButtons(Lang lang) {
        addButton.setText(lang.add());
        deleteButton.setText(lang.deleteBtn());
    }

    private void changeSelection(Book selectedBook, Boolean checked) {
        if (checked) {
            checkedBooks.add(selectedBook.getId());
        } else {
            checkedBooks.remove(selectedBook.getId());
        }
    }

    private void addButtonsHandlers() {
        editButtonColumn.setFieldUpdater((index, book, value) -> activity.onEditClick(book.getId()));
    }

    @UiHandler("addButton")
    void handleAddButtonClick(ClickEvent event) {
        activity.onAddClick();
    }

    @UiHandler("deleteButton")
    void handleDeleteButtonClick(ClickEvent event) {
        activity.onDeleteClick();
    }

    @UiField
    CellTable<Book> table;

    @UiField
    Button addButton;

    @UiField
    Button deleteButton;

    @Inject
    Lang lang;

    private Column<Book, String> editButtonColumn;

    private final List<Integer> checkedBooks = new ArrayList<>();

    private AbstractBookTableActivity activity;

    interface TableViewUiBinder extends UiBinder<HTMLPanel, BookTableView> {}
    private static final TableViewUiBinder tableUiBinder = GWT.create(TableViewUiBinder.class);
}
