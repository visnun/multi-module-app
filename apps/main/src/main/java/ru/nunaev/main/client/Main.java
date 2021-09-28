package ru.nunaev.main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import ru.nunaev.book.client.ui.bookform.AbstractBookForm;
import ru.nunaev.book.client.ui.booktable.AbstractBookTable;
import ru.nunaev.main.client.factory.WidgetFactory;
import ru.nunaev.main.event.*;
import ru.nunaev.model.client.Book;

import java.util.ArrayList;
import java.util.List;

public class Main implements EntryPoint {

    private final ReadingListServiceAsync readingListService = GWT.create(ReadingListService.class);

    private final WidgetFactory widgetFactory = GWT.create(WidgetFactory.class);

    AbstractBookTable bookTable = widgetFactory.getBookTable();

    AbstractBookForm bookForm = widgetFactory.getBookForm();

    List <Book> readingList = new ArrayList<>();

    Book bookForEdit = new Book();

    private final EventBus eventBus = new SimpleEventBus();

    @Override
    public void onModuleLoad() {
        addEventHandlers();
        addHandlers();
        eventBus.fireEvent(new ShowTableEvent());
        updateTable();

    }

    private void showTable() {
        RootPanel.get().clear();
        RootPanel.get().add(bookTable);
    }

    public void showAddForm() {
        bookForEdit = new Book();
        RootPanel.get().clear();
        RootPanel.get().add(bookForm);
    }

    public void showEditForm(Book book) {
        bookForm.getBookTitle().setText(book.getTitle());
        bookForm.getAuthor().setText(book.getAuthor());
        bookForm.getPages().setText(book.getPages());
        bookForm.getLanguage().setText(book.getLanguage());

        RootPanel.get().clear();
        RootPanel.get().add(bookForm);
    }

    public void clearFormView() {
        bookForm.getAuthor().setText("");
        bookForm.getBookTitle().setText("");
        bookForm.getPages().setText("");
        bookForm.getLanguage().setText("");
    }


    public void updateTable() {
        readingListService.getReadingList(new AsyncCallback<List<Book>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Book> books) {
                readingList.clear();
                readingList.addAll(books);
                bookTable.getTable().setRowCount(readingList.size(), true);
                bookTable.getTable().setRowData(0, readingList);
            }
        });
        bookTable.getSelectedBooks().clear();
    }

    public void sendAndSaveBook(Book book) {
        book.setTitle(bookForm.getBookTitle().getText());
        book.setAuthor(bookForm.getAuthor().getText());
        book.setPages(bookForm.getPages().getText());
        book.setLanguage(bookForm.getLanguage().getText());

        readingListService.save(book, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Void result) {

            }
        });
    }

    public void deleteBooks() {
        readingListService.delete(bookTable.getSelectedBooks(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(Void result) {

            }
        });
    }

    public void addEventHandlers() {
        eventBus.addHandler(ShowBookFormEvent.TYPE, event -> {
            clearFormView();
            showAddForm();
        });

        eventBus.addHandler(ShowTableEvent.TYPE, event -> showTable());

        eventBus.addHandler(SaveBookEvent.TYPE, event -> {
            sendAndSaveBook(bookForEdit);
            showTable();
            updateTable();
        });

        eventBus.addHandler(DeleteBookEvent.TYPE, event -> {
            deleteBooks();
            showTable();
            updateTable();
        });

        eventBus.addHandler(DoubleClickEditBookEvent.TYPE, event -> {
            int index = ((CellTable) event.getSource()).getKeyboardSelectedRow();
            bookForEdit = readingList.get(index);
            showEditForm(bookForEdit);
        });
    }


    public void addHandlers() {
        bookTable.getCreateButton().addClickHandler(event -> {
            eventBus.fireEvent(new ShowBookFormEvent());
        });

        bookForm.getSaveButton().addClickHandler(event -> eventBus.fireEvent(new SaveBookEvent()));

        bookForm.getCancelButton().addClickHandler(event -> eventBus.fireEvent(new ShowTableEvent()));

        bookTable.getTable().addDomHandler(event -> eventBus.fireEvent(new DoubleClickEditBookEvent()), DoubleClickEvent.getType()); // TODO разобраться как передать параметр

        bookTable.getDeleteButton().addClickHandler(event -> eventBus.fireEvent(new DeleteBookEvent()));
    }
}
