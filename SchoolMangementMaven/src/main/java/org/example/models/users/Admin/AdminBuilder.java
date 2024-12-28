package org.example.models.users.Admin;

public class AdminBuilder {
  private String id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;


  public String getId() {
    return id;
  }

  public AdminBuilder setId(String id) {
    this.id = id;
    return this ;

  }

  public String getLogin() {
    return login;
  }

  public AdminBuilder setLogin(String login) {
    this.login = login;
    return this ;
  }

  public String getPassword() {
    return password;

  }

  public AdminBuilder setPassword(String password) {
    this.password = password;
    return this ;

  }

  public String getFirstName() {
    return firstName;
  }

  public AdminBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this ;

  }

  public String getLastName() {
    return lastName;
  }

  public AdminBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this ;

  }

  public Admin build(){
    return new Admin(id ,login , password , firstName , lastName ) ;
  }
}
