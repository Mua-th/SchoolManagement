package org.example.repositories.Prof;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.user.Prof.Professor;
import org.example.models.user.Prof.ProfessorBuilder;
import org.example.models.user.User.User;
import org.example.repositories.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfessorDaoImpl implements Repository<User,String> {
  Database database = MySQLDatabase.getInstance();



  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public boolean save(User professor) throws SQLException {
    try {
      database.executeQuery(String.format("insert into Users values '%s' , '%s' , '%s'  , '%s'  , '%s', '%s' ",
        professor.getId(),
        professor.getLogin(),
        professor.getPassword(),
        professor.getFirstName(),
        professor.getLastName(),
        professor.getRole()));
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public boolean delete(String s) {
    return false;
  }

  @Override
  public User findById(String id) throws SQLException {
    Database dbConnection = MySQLDatabase.getInstance();
    Connection connection = dbConnection.connect();
    ResultSet rs = dbConnection.fetchResults(String.format("SELECT * FROM Users where id ='%s';" , id));
    String login ="" , firstName="" , lastName="" ;
    while (rs.next()) {
       login = rs.getString("login");
       firstName = rs.getString("firstName");
       lastName = rs.getString("lastName");
    }
    Professor prof = new ProfessorBuilder().setId(id)
      .setFirstName(firstName)
      .setLastName(lastName).setLogin(login).build() ;
    dbConnection.disconnect();
    return prof;
  }




}
