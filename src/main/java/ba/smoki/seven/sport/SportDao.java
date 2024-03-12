package ba.smoki.seven.sport;

import ba.smoki.six.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportDao implements Dao<Sport> {
    @Override
    public List<Sport> findAll() {
        String sqlSelect = """
                SELECT * FROM sports
                """;
        List<Sport> sports = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(sqlSelect);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sport sport = new Sport();
                sport.setId(rs.getLong("id"));
                sport.setName(rs.getString("name"));
                sport.setDescription(rs.getString("description"));
                sports.add(sport);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return sports;
    }

    @Override
    public Sport findById(Long id) {
        String sqlSelect = """
                SELECT * FROM sports
                WHERE id=?
                """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement ps = connection.prepareStatement(sqlSelect);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Sport sport = new Sport();
                sport.setId(rs.getLong("id"));
                sport.setName(rs.getString("name"));
                sport.setDescription(rs.getString("description"));
                return sport;
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return null;
    }


    @Override
    public void update(Sport entity) {

    }

    @Override
    public void delete(Sport entity) {

    }

    @Override
    public void save(Sport entity) {

    }

    @Override
    public String tableName() {
        return "sports";
    }
}
