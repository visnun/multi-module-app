package ru.nunaev.model.server.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.nunaev.model.client.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setAuthor(resultSet.getString("author"));
        book.setPages(resultSet.getString("pages"));
        book.setLanguage(resultSet.getString("language"));
        return book;
    }
}
