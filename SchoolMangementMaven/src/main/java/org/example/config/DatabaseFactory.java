package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseFactory {

  public static Database getDatabaseInstance() {
    Properties properties = new Properties();
    try (InputStream input = DatabaseFactory.class.getClassLoader().getResourceAsStream("app.properties")) {
      if (input == null) {
        throw new RuntimeException("Unable to find app.properties");
      }
      properties.load(input);
    } catch (IOException ex) {
      throw new RuntimeException("Error reading app.properties", ex);
    }

    String databaseType = properties.getProperty("database.type");
    String url = properties.getProperty("database.url");
    String username = properties.getProperty("database.username");
    String password = properties.getProperty("database.password");

    if ("mysql".equalsIgnoreCase(databaseType)) {
      return new MySQLDatabase(url, username, password);
    } else {
      throw new IllegalArgumentException("Unsupported database type: " + databaseType);
    }
  }

}