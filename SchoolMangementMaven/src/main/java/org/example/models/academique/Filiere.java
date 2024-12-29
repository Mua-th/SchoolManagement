package org.example.models.academique;

import java.util.ArrayList;
import java.util.List;

public class Filiere {
private String code;
private String name;
private List<Module> modules;

public Filiere(String code, String name) {
  this.code = code;
  this.name = name;
  this.modules = new ArrayList<>();
  }
  // Getters and Setters
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Module> getModules() {
    return modules;
  }

  public void setModules(List<Module> modules) {
    this.modules = modules;
  }

  @Override
  public String toString() {
    return "Filiere{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", modules=" + modules +
            '}';
  }
}