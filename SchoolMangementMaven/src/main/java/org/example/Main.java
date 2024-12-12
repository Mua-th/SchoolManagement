package org.example;

import org.example.config.Database;
import org.example.config.MySQLDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    Database dbConnection = MySQLDatabase.getInstance();
    Connection connection = dbConnection.connect();
    ResultSet rs = dbConnection.fetchResults("SELECT * FROM Users;");

    while (rs.next()) {
      // Assuming the Users table has columns: id, login, firstName, lastName, role
      String id = rs.getString("id");
      String login = rs.getString("login");
      String firstName = rs.getString("firstName");
      String lastName = rs.getString("lastName");
      String role = rs.getString("role");

      // Print the fetched data
      System.out.println("ID: " + id);
      System.out.println("Login: " + login);
      System.out.println("First Name: " + firstName);
      System.out.println("Last Name: " + lastName);
      System.out.println("Role: " + role);
      System.out.println("-------------------------");
    }

    // Disconnect from the database
    dbConnection.disconnect();


    System.out.println("Database connected successfully!");
  }
}