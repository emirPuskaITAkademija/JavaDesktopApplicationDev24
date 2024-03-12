package ba.smoki.seven.player;

import ba.smoki.seven.sport.Sport;
import ba.smoki.seven.sport.SportDao;
import ba.smoki.six.ColorConvertor;
import ba.smoki.six.Dao;

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
public class PlayerDao implements Dao<Player> {


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
                Long sportId = resultSet.getLong("sport_id");
                SportDao sportDao = new SportDao();
                Sport sport = sportDao.findById(sportId);
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
    public Player findById(Long id) {
        return null;
    }


    @Override
    public void update(Player player) {
        String sqlUpdate = """
                  UPDATE players 
                  SET name=?, surname=?, color=?, sport_id=?, years=?, vegetarian=?
                  WHERE id=?
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            ColorConvertor colorConvertor = new ColorConvertor();
            String color = colorConvertor.toColorString(player.getColor());
            preparedStatement.setString(3, color);
            preparedStatement.setLong(4, player.getSport().getId());
            preparedStatement.setInt(5, player.getYears());
            preparedStatement.setBoolean(6, player.getVegetarian());
            preparedStatement.setLong(7, player.getId());
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void delete(Player player) {
        String sqlDelete = """
                  DELETE FROM players WHERE id=?;
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setLong(1, player.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void save(Player player) {
        String sqlInsert = """
                INSERT INTO players 
                (`name`, `surname`, `color`, `sport_id`, `years`, `vegetarian`) 
                VALUES (?, ?, ?, ?, ?, ?)  
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getSurname());
            ColorConvertor colorConvertor = new ColorConvertor();
            String color = colorConvertor.toColorString(player.getColor());
            preparedStatement.setString(3, color);
            preparedStatement.setLong(4, player.getSport().getId());
            preparedStatement.setInt(5, player.getYears());
            preparedStatement.setBoolean(6, player.getVegetarian());
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public String tableName() {
        return "players";
    }
}
