package org.example.services.user;

import org.example.models.user.User.User;
import org.example.repositories.User.UserRepository;

import java.sql.SQLException;

public class UserService {
  UserRepository userRepository ;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findByLogin(String login) throws SQLException {
   User user = userRepository.findByLogin(login) ;
   return user ;
  }
}
