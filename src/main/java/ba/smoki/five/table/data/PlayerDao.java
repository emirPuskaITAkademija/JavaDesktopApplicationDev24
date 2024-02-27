package ba.smoki.five.table.data;

import java.sql.*;
import java.util.Collections;
import java.util.List;

public class PlayerDao {


    public static final String URL = "jdbc:mysql://localhost:3306/players";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public List<Player> selectPlayers(){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);){
            String sqlStatement = "SELECT * FROM players";
            PreparedStatement preparedStatement =  connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(2));
            }
        }catch (SQLException exception){
            System.err.println(exception.getMessage());
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        new PlayerDao().selectPlayers();
    }
}
