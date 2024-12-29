package org.example.models.academique;

import java.util.List;

public class Module {
  private String code;
  private String name;
  private Filiere filiere;
  private Semester semester;
  private List<ModuleElement> moduleElements;

  // Constructeur par défaut
  public Module() {
  }

  // Constructeur avec tous les attributs
  public Module(String code, String name, Filiere filiere, Semester semester, List<ModuleElement> moduleElements) {
    this.code = code;
    this.name = name;
    this.filiere = filiere;
    this.semester = semester;
    this.moduleElements = moduleElements;
  }

  // Constructeur avec tous les attributs
  public Module(String code, String name) {
    this.code = code;
    this.name = name;
  }

  // Getters
  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public Filiere getFiliere() {
    return filiere;
  }

  public Semester getSemester() {
    return semester;
  }

  public List<ModuleElement> getModuleElements() {
    return moduleElements;
  }

  // Setters
  public void setCode(String code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFiliere(Filiere filiere) {
    this.filiere = filiere;
  }

  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  public void setModuleElements(List<ModuleElement> moduleElements) {
    this.moduleElements = moduleElements;
  }


}