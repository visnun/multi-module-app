package ru.nunaev.main.server.utils;

import ru.nunaev.model.client.Book;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataGenerator {
    public static List<Book> getReadingList() {
        List<Book> readingList = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Война и мир");
        book1.setAuthor("Л. Толстой");
        book1.setPages("1500");
        book1.setLanguage("Русский");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("Книга пяти колец");
        book2.setAuthor("М. Мусаси");
        book2.setPages("300");
        book2.setLanguage("Английский");

        readingList.add(book1);
        readingList.add(book2);

        return readingList;
    }

}
