package ru.nunaev.main.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import ru.nunaev.book.client.ui.BookForm;
import ru.nunaev.book.client.ui.BookTable;
import ru.nunaev.model.client.Book;


import java.util.ArrayList;
import java.util.List;

public class Main implements EntryPoint {

    private final ReadingListServiceAsync readingListService = GWT.create(ReadingListService.class);

    BookTable bookTable = new BookTable();

    BookForm bookForm = new BookForm();

    List <Book> readingList = new ArrayList<>();

    Book bookForEdit = new Book();

    @Override
    public void onModuleLoad() {
        showTable();
        updateTable();
        addHandlers();
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

    public void addHandlers() {
        bookTable.getCreateButton().addClickHandler(event -> {
            clearFormView();
            showAddForm();
        });

        bookForm.getSaveButton().addClickHandler(event -> {
            sendAndSaveBook(bookForEdit);
            showTable();
            updateTable();
        });

        bookForm.getCancelButton().addClickHandler(event -> showTable());

        bookTable.getTable().addDomHandler(event -> {
            int index = ((CellTable) event.getSource()).getKeyboardSelectedRow();
            bookForEdit = readingList.get(index);
            showEditForm(bookForEdit);
        }, DoubleClickEvent.getType());

        bookTable.getDeleteButton().addClickHandler(event -> {
            deleteBooks();
            showTable();
            updateTable();
        });
    }
}
