package org.example.models.user;

public abstract class User {
  private String id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private UserRole role;
  public String getId() {
    return id;
  }
  public String getLogin() {
    return login;
  }
  @Override
  public String toString() {
    return "User{" +
      "id='" + id + '\'' +
      ", login='" + login + '\'' +
      ", password='" + password + '\'' +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", role=" + role +
      '}';
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setLogin(String login) {
    this.login = login;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setRole(UserRole role) {
    this.role = role;
  }
  public String getPassword() {
    return password;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public UserRole getRole() {
    return role;
  }
  public User(String login, String password, String firstName, String lastName, UserRole role) {
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }
  public User(String id ,String login, String password, String firstName, String lastName, UserRole role) {
    this.id = id ;
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }
}