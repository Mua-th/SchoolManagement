package org.example.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {
  static Database getInstance() {
    throw new UnsupportedOperationException("This method should be implemented by the implementing class.");
  }
  Connection connect() throws SQLException;
  void disconnect() throws SQLException;
  void executeQuery(String query) throws SQLException;
  ResultSet fetchResults(String query) throws SQLException;
  void executeStatement(PreparedStatement stmt) throws SQLException;
  ResultSet fetchResults(PreparedStatement stmt) throws SQLException;
}