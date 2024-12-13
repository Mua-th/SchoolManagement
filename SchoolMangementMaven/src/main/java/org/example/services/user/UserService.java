package org.example.services.user;

import org.example.repositories.UserRepository;

public class UserService {
  UserRepository userRepository ;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
