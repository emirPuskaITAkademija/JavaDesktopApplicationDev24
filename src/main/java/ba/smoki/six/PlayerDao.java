package ba.smoki.six;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC
 * <li>Connection</li>
 * <li>Statement, PreparedStatement</li>
 * <li>ResultSet, ResultSetMetaData</li>
 */
public class PlayerDao implements Dao<Player>{
    public static final String URL = "jdbc:mysql://localhost:3306/players";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlStatement = "SELECT * FROM players";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String colorString = resultSet.getString("color");
                ColorConvertor colorConvertor = new ColorConvertor();
                Color color = colorConvertor.toColor(colorString);
                String sport = resultSet.getString("sport");
                Integer years = resultSet.getInt("years");
                Boolean vegetarian = resultSet.getBoolean("vegetarian");
                Player player = new Player(id, name, surname, color, sport, years, vegetarian);
                players.add(player);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return players;
    }

    @Override
    public List<String> findColumnNames(){
        List<String> columnNames = new ArrayList<>();
        String sqlQuery = "SELECT * FROM players";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for(int i = 1; i<=columnCount; i++){
                String columnName = resultSetMetaData.getColumnName(i);
                columnNames.add(columnName);
            }
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        return columnNames;
    }



    @Override
    public void update(Player player){
        String sqlUpdate = """
                  UPDATE players 
                  SET name=?, surname=?, color=?, sport=?, years=?, vegetarian=?
                  WHERE id=?
                """;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            ColorConvertor colorConvertor = new ColorConvertor();
            String color = colorConvertor.toColorString(player.getColor());
            preparedStatement.setString(3, color);
            preparedStatement.setString(4, player.getSport());
            preparedStatement.setInt(5, player.getYears());
            preparedStatement.setBoolean(6, player.getVegetarian());
            preparedStatement.setLong(7, player.getId());
            preparedStatement.execute();
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
    }
}
