package org.example.models.user;

abstract class User {
  private String id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private UserRole role;

  public User(String login, String password, String firstName, String lastName, UserRole role) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

}