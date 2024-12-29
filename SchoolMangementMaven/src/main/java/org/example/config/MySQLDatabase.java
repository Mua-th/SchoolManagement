package org.example.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLDatabase implements Database {
  private static final String URL = "jdbc:mysql://localhost:3306/schoolmanagement";
  private static final String USER = "root";
  private static final String PASSWORD = "";
  private Connection connection;
  private static MySQLDatabase instance;
  private MySQLDatabase() {
  }
  public static MySQLDatabase getInstance() {
    if (instance == null) {
      synchronized (MySQLDatabase.class) {
        if (instance == null) {
          instance = new MySQLDatabase();
        }
      }
    }
    return instance;
  }
  @Override
  public Connection connect() throws SQLException {
    if (connection == null || connection.isClosed()) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to MySQL database.");
      } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL Driver not found!", e);
      }
    }
    return connection;
  }
  @Override
  public void disconnect() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
      System.out.println("Disconnected from MySQL database.");
    }
  }
  @Override
  public void executeQuery(String query) throws SQLException {
    try (var stmt = connection.createStatement()) {
      stmt.execute(query);
    }
  }
  @Override
  public ResultSet fetchResults(String query) throws SQLException {
    var stmt = connection.createStatement();
    return stmt.executeQuery(query);
  }
}
