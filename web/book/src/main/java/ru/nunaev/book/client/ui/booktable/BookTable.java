package ru.nunaev.book.client.ui.booktable;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import ru.nunaev.book.client.lang.Lang;
import ru.nunaev.book.event.DeleteBookEvent;
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowEditBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookTable extends Composite implements AbstractBookTable {
    @Inject
    public void init() {
        initWidget(tableUiBinder.createAndBindUi(this));
        initTable(lang);
        initButtons(lang);
        addButtonsHandlers();
        addEventHandlers();
    }

    @Override
    public CellTable<Book> getTable() {
        return table;
    }

    @Override
    public Button getAddButton() {
        return addButton;
    }

    @Override
    public Set<Integer> getSelectedBooks() {
        return checkedBooks;
    }

    @Override
    public Button getDeleteButton() {
        return deleteButton;
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
                return BookTable.this.lang.edit();
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

    private void showTable() {
        readingList.clear();

        readingListService.getReadingList(new AsyncCallback<List<Book>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Не удалось получить список книг");
            }

            @Override
            public void onSuccess(List<Book> books) {
                readingList.addAll(books);
                table.setRowCount(readingList.size(), true);
                table.setRowData(0, readingList);
            }
        });

        checkedBooks.clear();
        RootPanel.get().clear();
        RootPanel.get().add(this);
    }

    private void deleteBooks() {
        readingListService.delete(checkedBooks, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка при удалении");
            }

            @Override
            public void onSuccess(Void result) {
                eventBus.fireEvent(new ShowTableEvent());
            }
        });
    }

    private void addEventHandlers() {
        eventBus.addHandler(ShowTableEvent.TYPE, event -> showTable());
    }

    private void addButtonsHandlers() {
        editButtonColumn.setFieldUpdater((index, book, value) -> eventBus.fireEvent(new ShowEditBookFormEvent(book)));
    }

    @UiHandler("addButton")
    void handleAddButtonClick(ClickEvent event) {
        eventBus.fireEvent(new ShowBookFormEvent());
    }

    @UiHandler("deleteButton")
    void handleDeleteButtonClick(ClickEvent event) {
        deleteBooks();
    }

    @UiField
    CellTable<Book> table;

    @UiField
    Button addButton;

    @UiField
    Button deleteButton;

    @Inject
    ReadingListServiceAsync readingListService;

    @Inject
    EventBus eventBus;

    @Inject
    Lang lang;

    private Column<Book, String> editButtonColumn;

    private final List<Book> readingList = new ArrayList<>();

    private final Set<Integer> checkedBooks = new HashSet<>();


    interface TableViewUiBinder extends UiBinder<HTMLPanel, BookTable> {}
    private static final TableViewUiBinder tableUiBinder = GWT.create(TableViewUiBinder.class);
}
