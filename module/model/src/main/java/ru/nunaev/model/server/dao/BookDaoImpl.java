package ru.nunaev.model.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nunaev.model.client.Book;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Book> getAll() {
        final String sql = "SELECT * FROM tb_books";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public Book getById(Integer id) {
        final String query = "SELECT * FROM tb_books WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new Object[] { id }, new BookMapper());
    }

    @Override
    public void save(Book book) {
        if (book.getId() == 0) {
            String insertQuery = "INSERT INTO tb_books (title, author, pages, language) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update( insertQuery, book.getTitle(), book.getAuthor(), book.getPages(), book.getLanguage());
            return;
        }
        String SQL = "UPDATE tb_books SET title = ?, author = ?, pages = ?, language = ? WHERE id = ?";
        jdbcTemplate.update(SQL, book.getTitle(), book.getAuthor(), book.getPages(), book.getLanguage(), book.getId());
    }

    @Override
    public void delete(List<Integer> bookIdList) {
        String SQL = "DELETE FROM tb_books WHERE id = ?";
        jdbcTemplate.batchUpdate(SQL,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, bookIdList.get(i));
                        System.out.println(ps.getMetaData());
                        System.out.println(bookIdList.get(i));
                    }

                    @Override
                    public int getBatchSize() {
                        return bookIdList.size();
                    }
                });
    }

    private JdbcTemplate jdbcTemplate;
}
