package org.example.models.user.Prof;

import org.example.models.academique.ModuleElement;
import org.example.models.user.User.UserRole;

import java.util.List;

public class ProfessorBuilder {
  private String id;
  private String login;
  private String password;
  private String firstName;
  private String lastName;
  private List<ModuleElement> moduleElementList ;

  private UserRole role = UserRole.Professor;

  public ProfessorBuilder setId(String id) {
    this.id = id;
    return this ;

  }

  public ProfessorBuilder setLogin(String login) {
    this.login = login;
    return this ;

  }

  public ProfessorBuilder setPassword(String password) {
    this.password = password;
    return this ;

  }

  public ProfessorBuilder setFirstName(String firstName) {
    this.firstName = firstName;
    return this ;

  }

  public ProfessorBuilder setLastName(String lastName) {
    this.lastName = lastName;
    return this ;
  }

  public void setModuleElementList(List<ModuleElement> moduleElementList) {
    this.moduleElementList = moduleElementList;
  }

  public Professor build(){
    return new Professor(this.id ,this.login , password ,firstName, lastName ) ;
  }
}
