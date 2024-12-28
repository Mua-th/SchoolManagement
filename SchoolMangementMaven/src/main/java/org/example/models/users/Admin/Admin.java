package org.example.models.users.Admin;

import org.example.models.users.User.User;
import org.example.models.users.User.UserRole;

public class Admin extends User {


  public Admin(String login, String password, String firstName, String lastName) {
    super(login, password, firstName, lastName, UserRole.Administrator);
  }

  public Admin( String id , String login, String password, String firstName, String lastName) {
    super( id , login, password, firstName, lastName, UserRole.Administrator);
  }

}
