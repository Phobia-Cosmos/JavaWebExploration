package org.lzh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class MyService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MyObject> getAllObjects() {
        return jdbcTemplate.query("SELECT * FROM my_objects", (resultSet, i) -> {
            MyObject obj = new MyObject();
            obj.setId(resultSet.getLong("id"));
            obj.setName(resultSet.getString("name"));
            obj.setDescription(resultSet.getString("description"));
            return obj;
        });
    }

    public void createObject(MyObject obj) {
        jdbcTemplate.update("INSERT INTO my_objects (name, description) VALUES (?, ?)",
                obj.getName(), obj.getDescription());
    }

    public void updateObject(Long id, MyObject obj) {
        jdbcTemplate.update("UPDATE my_objects SET name = ?, description = ? WHERE id = ?",
                obj.getName(), obj.getDescription(), id);
    }

    public void deleteObject(Long id) {
        jdbcTemplate.update("DELETE FROM my_objects WHERE id = ?", id);
    }
}
