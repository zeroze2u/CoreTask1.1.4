package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = Util.GetConnection();
            Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS `users`.`user_table` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `lastName` VARCHAR(45) NULL,\n" +
                    "  `age` TINYINT(3) NULL,\n" +
                    "  PRIMARY KEY (`id`))");
        System.out.println("Таблица была создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.GetConnection();
            Statement statement = connection.createStatement()){
            statement.execute("DROP TABLE IF EXISTS user_table");
            System.out.println("Таблица была удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.GetConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_table(name, lastname, age) VALUES(?,?,?)")){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("Юзер с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.GetConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_table WHERE id = ?")){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Юзер удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        try (Connection connection = Util.GetConnection();
             ResultSet resultSet = connection.createStatement()
                     .executeQuery("SELECT * FROM user_table")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                all.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.GetConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE user_table");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
