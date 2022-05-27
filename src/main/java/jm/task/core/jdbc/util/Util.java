package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getMYSQLConnection () throws SQLException, ClassNotFoundException {
        String conURL = "jdbc:mysql://localhost:3306/task";
        String userName = "root";
        String userPassword = "1408";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(conURL,userName,userPassword);
        return connection;
    }
}
