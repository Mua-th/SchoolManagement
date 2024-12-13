package org.example.models.user;

import org.example.models.academique.ModuleElement;

import java.util.List;

public class Professor extends User {
  private List<ModuleElement> moduleElementList ;
  public Professor(String id , String login, String password, String firstName, String lastName) {
    super(id ,login, password, firstName, lastName, UserRole.Professor);
  }

  public Professor(String login, String password, String firstName, String lastName, List<ModuleElement> moduleElementList) {
    super(login, password, firstName, lastName, UserRole.Professor);
    this.moduleElementList = moduleElementList;
  }
  public Professor(String id ,String login, String password, String firstName, String lastName, List<ModuleElement> moduleElementList) {
    super(id , login, password, firstName, lastName, UserRole.Professor);
    this.moduleElementList = moduleElementList;
  }

  public void setModuleElementList(List<ModuleElement> moduleElementList) {
    this.moduleElementList = moduleElementList;
  }

  public List<ModuleElement> getModuleElementList() {
    return moduleElementList;
  }
}
