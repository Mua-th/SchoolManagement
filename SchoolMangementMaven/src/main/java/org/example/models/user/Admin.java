package org.example.models.user;

public class Admin extends User{


  public Admin(String login, String password, String firstName, String lastName) {
    super(login, password, firstName, lastName, UserRole.Administrator);
  }

  public Admin( String id , String login, String password, String firstName, String lastName) {
    super( id , login, password, firstName, lastName, UserRole.Administrator);
  }

}
