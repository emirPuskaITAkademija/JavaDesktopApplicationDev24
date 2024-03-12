package ba.smoki.six;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface Dao<E> {
    String URL = "jdbc:mysql://localhost:3306/players";
    String USER = "root";
    String PASSWORD = "root";

    List<E> findAll();

    E findById(Long id);


    void update(E entity);

    void delete(E entity);

    void save(E entity);

    String tableName();

    //DRY Don't Repeat
    default List<String> findColumnNames() {
        List<String> columnNames = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + tableName();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                columnNames.add(columnName);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return columnNames;
    }
}
