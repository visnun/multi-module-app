package ru.nunaev.book.client.ui.booktable;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
import ru.nunaev.book.event.ShowBookFormEvent;
import ru.nunaev.book.event.ShowTableEvent;
import ru.nunaev.common.client.ReadingListServiceAsync;
import ru.nunaev.model.client.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookTable extends Composite implements AbstractBookTable {
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

    Book bookForEdit = new Book();

    List<Book> readingList = new ArrayList<>();

    private Set<Integer> checkedBooks = new HashSet<>();

    @Inject
    public void init(Lang lang) {
        initWidget(tableUiBinder.createAndBindUi(this));
        addHandlers();
        addEventHandlers();
        initTable(lang);
        initButtons(lang);
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

    public void initTable(Lang lang) {
        Column<Book, Boolean> checkColumn = new Column<Book, Boolean>(new CheckboxCell(true, false)) {
            @Override
            public Boolean getValue(Book object) {
                return null;
            }
        };
        checkColumn.setFieldUpdater(new FieldUpdater<Book, Boolean>() {
            @Override
            public void update(int index, Book book, Boolean checked) {
                BookTable.this.changeSelection(book, checked);
            }
        });
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
    }

    public void initButtons(Lang lang) {
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

    public void showTable() {
        readingList.clear();

        readingListService.getReadingList(new AsyncCallback<List<Book>>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Ошибка");
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

    public void addEventHandlers() {
        eventBus.addHandler(ShowTableEvent.TYPE, event -> showTable());
    }


    public void addHandlers() {
        addButton.addClickHandler(event -> eventBus.fireEvent(new ShowBookFormEvent()));
    }

    interface TableViewUiBinder extends UiBinder<HTMLPanel, BookTable> {}
    private static final TableViewUiBinder tableUiBinder = GWT.create(TableViewUiBinder.class);
}
