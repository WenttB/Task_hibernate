package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                statement.executeUpdate("CREATE TABLE Users (id MEDIUMINT NOT NULL AUTO_INCREMENT," +
                        " name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY(id))");
                conn.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }

    }

    public void dropUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                statement.executeUpdate("DROP TABLE Users");
                conn.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {}
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {}
        }
    }

        public void saveUser(String name, String lastName, byte age) {
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement pr = conn.prepareStatement("INSERT INTO Users (name, lastName, age) values(?,?,?)");
                pr.setString(1,name);
                pr.setString(2,lastName);
                pr.setByte(3,age);
                pr.executeUpdate();
                conn.commit();
            }
            } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }


    public void removeUserById(long id) {
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE id = ?");
                ps.setLong(1,id);
                ps.executeUpdate();
                conn.commit();
            }
            } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    users.add(new User(rs.getString("name"),rs.getString("lastName"),rs.getByte("age")));
                }
                conn.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getMYSQLConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Users");
                ps.executeUpdate();
                conn.commit();
            }
        } catch (SQLException | ClassNotFoundException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }

        }
    }
}
