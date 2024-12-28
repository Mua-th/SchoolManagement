package org.example.models.academique;

import java.util.List;

public class Module {
  private String code;
  private String name;

  private Filiere filiere;
  private Semester semester;
  private List<ModuleElement> moduleElements;

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

  public Filiere getFiliere() {
    return filiere;
  }

  public void setFiliere(Filiere filiere) {
    this.filiere = filiere;
  }

  public Semester getSemester() {
    return semester;
  }

  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  public List<ModuleElement> getModuleElements() {
    return moduleElements;
  }

  public void setModuleElements(List<ModuleElement> moduleElements) {
    this.moduleElements = moduleElements;
  }



}