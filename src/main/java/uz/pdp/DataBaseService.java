package uz.pdp;

import java.sql.*;

public class DataBaseService {
    private String url = "jdbc:postgresql://localhost:5432/app-jdbc-example";
    private String dbUser = "postgres";
    private String dbPassword = "postgres";

    public void saveUser(User user) {
        try {
            Connection ulash = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = ulash.createStatement();
            String query = "insert into users(first_name, last_name, username, password) values('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getUserName() + "','" + user.getPassword() + "');";
            statement.execute(query);
            System.out.println("User added. Successfully");
            statement.close();
            ulash.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getUsers() {
        Connection ulash = null;
        try {
            ulash = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = ulash.createStatement();
            String query = "select * from users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                User user = new User(id, firstName, lastName, username, password);
                System.out.println(user);
            }
            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUsers(int usersID) {
        try {
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "delete from users where ID = " + usersID;
            boolean execute = statement.execute(query);
            System.out.println("User deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            String query = "update users set ";
            if (!user.getFirstName().isEmpty()) {
                query = query + "first_name='" + user.getFirstName() + "',";
            }
            if (!user.getLastName().isEmpty()) {
                query = query + "last_name='" + user.getLastName() + "',";
            }
            if (!user.getUserName().isEmpty()) {
                query = query + "username='" + user.getUserName() + "',";
            }
            if (!user.getPassword().isEmpty()) {
                query = query + "password='" + user.getPassword() + "'";
            }

            if (!query.equals("update users set ")) {
                if (query.endsWith("',")) {
                    query = query.substring(0, query.length() - 1);
                }
                query = query + "where id=   " + user.getId();
                statement.execute(query);
                System.out.println("Updated");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
