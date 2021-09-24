package ru.nunaev.model.client;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private String pages;
    private String language;

    public Book() {
    }

    public Book(String title, String author, String pages, String language) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
