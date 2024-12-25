package org.example.repositories.User;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.user.User.User;
import org.example.models.user.User.UserFactory;
import org.example.repositories.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository implements UserDAO, Repository<User,String> {

  private static UserRepository instance ;

  private Database dbConnection = MySQLDatabase.getInstance();

  public static UserRepository getInstance(){
    if(instance==null){
      instance = new UserRepository() ;
    }
    return instance ;
  }

  @Override
  public User findById(String id) throws SQLException {
    Connection connection = dbConnection.connect();
    ResultSet rs = dbConnection.fetchResults(String.format("SELECT * FROM Users where id ='%s';" , id));
    String login ="" , firstName="" , lastName="" ,role = "" , password="" ;
    User user = null;
    while (rs.next()) {
      login = rs.getString("login");
      password = rs.getString("password");
      firstName = rs.getString("firstName");
      lastName = rs.getString("lastName");
      role = rs.getString("role");
    }
    dbConnection.disconnect();


    user = UserFactory.createUser(id , password , firstName,lastName , login , role);

    return user;
  }

  @Override
  public List<User> findAll() {
    return null;
  }

  @Override
  public boolean save(User user) throws SQLException {
    return false;
  }

  @Override
  public boolean delete(String s) {
    return false;
  }

  @Override
  public User findByLogin(String login) throws SQLException {
    Connection connection = dbConnection.connect();

    ResultSet rs = dbConnection.fetchResults(String.format("SELECT * FROM Users where login ='%s';" , login));
     String id ="", firstName="" , lastName="" ,role = "" , password="" ;
    User user = null;
    while (rs.next()) {
      id = rs.getString("id");
      password = rs.getString("password");
      firstName = rs.getString("firstName");
      lastName = rs.getString("lastName");
      role = rs.getString("role");
    }
    dbConnection.disconnect();


    user = UserFactory.createUser(id , password , firstName,lastName , login , role);
    return user;

  }
}
