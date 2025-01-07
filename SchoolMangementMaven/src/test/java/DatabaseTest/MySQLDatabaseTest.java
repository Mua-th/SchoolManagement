package DatabaseTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class MySQLDatabaseTest {

  private String url;
  private String username;
  private String password;
  private Connection connection;

  @BeforeEach
  public void setUp() throws IOException {
    Properties properties = new Properties();
    try (FileInputStream input = new FileInputStream("src/main/resources/app.properties")) {
      properties.load(input);
      url = properties.getProperty("database.url");
      username = properties.getProperty("database.username");
      password = properties.getProperty("database.password");
    }
  }

  @Test
  public void testConnection() {
    try {
      connection = DriverManager.getConnection(url, username, password);
      assertNotNull(connection);
      assertFalse(connection.isClosed());
    } catch (SQLException e) {
      fail("Connection failed: " + e.getMessage());
    }
  }

  @AfterEach
  public void tearDown() {
    if (connection != null) {
      try {
        connection.close();
        assertTrue(connection.isClosed());
      } catch (SQLException e) {
        fail("Disconnection failed: " + e.getMessage());
      }
    }
  }
}