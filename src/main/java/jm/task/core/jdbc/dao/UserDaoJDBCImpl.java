package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "  id int NOT NULL AUTO_INCREMENT,\n" +
                    "  first_name varchar(45) NOT NULL,\n" +
                    "  last_name varchar(45) NOT NULL,\n" +
                    "  age int NOT NULL,\n" +
                    "  PRIMARY KEY (id))");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql =  "INSERT INTO users (Name, LastName, Age) Values (?, ?, ?)";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }


    public void removeUserById(long id) {

        String sql = "DELETE FROM users WHERE ID=?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers()  {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT ID, Name, LastName, Age FROM users";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));

                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return usersList;
    }

    public void cleanUsersTable() {

    }
}
