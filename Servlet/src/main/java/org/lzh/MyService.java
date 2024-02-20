package org.lzh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyService {
    private Connection connection;

    public MyService(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, username, password);
    }

    public List<MyObject> getAllObjects() throws SQLException {
        List<MyObject> objects = new ArrayList<>();
        String sql = "SELECT * FROM my_objects";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                MyObject obj = new MyObject();
                obj.setId(resultSet.getLong("id"));
                obj.setName(resultSet.getString("name"));
                obj.setDescription(resultSet.getString("description"));
                objects.add(obj);
            }
        }
        return objects;
    }

    public void createObject(MyObject obj) throws SQLException {
        String sql = "INSERT INTO my_objects (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getDescription());
            statement.executeUpdate();
        }
    }

    public void updateObject(Long id, MyObject obj) throws SQLException {
        String sql = "UPDATE my_objects SET name = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getDescription());
            statement.setLong(3, id);
            statement.executeUpdate();
        }
    }

    public void deleteObject(Long id) throws SQLException {
        String sql = "DELETE FROM my_objects WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
