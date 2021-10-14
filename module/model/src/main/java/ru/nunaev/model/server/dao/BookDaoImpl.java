package ru.nunaev.model.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import ru.nunaev.model.client.Book;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Book> getAll() {

//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setUrl("jdbc:mysql://localhost:3306/myDb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("password");
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final String sql = "SELECT * FROM tb_books";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public Book getById(Integer id) {
        final String query = "SELECT * FROM tb_books WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, new Object[] { id }, new BookMapper());
    }

    private JdbcTemplate jdbcTemplate;
}
