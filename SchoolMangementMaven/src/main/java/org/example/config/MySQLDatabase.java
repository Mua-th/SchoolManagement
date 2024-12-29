package org.example.config;

import java.sql.*;

public class MySQLDatabase implements Database {
  private static MySQLDatabase instance;
  private final String url;
  private final String user;
  private final String password;
  private Connection connection;

  MySQLDatabase(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
  }

  public static MySQLDatabase getInstance(String url, String user, String password) {
    if (instance == null) {
      instance = new MySQLDatabase(url, user, password);
    }
    return instance;
  }

  @Override
  public Connection connect() throws SQLException {
    if (connection == null || connection.isClosed()) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
       // System.out.println("Connected to MySQL database.");
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
     // System.out.println("Disconnected from MySQL database.");
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

  @Override
  public void executeStatement(PreparedStatement stmt) throws SQLException {
    stmt.execute();
  }

  @Override
  public ResultSet fetchResults(PreparedStatement stmt) throws SQLException {
    return stmt.executeQuery();
  }
}