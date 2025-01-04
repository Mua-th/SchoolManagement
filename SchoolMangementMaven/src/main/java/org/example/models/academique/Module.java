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

  public Module(String code) {
    this.code = code;
  }

  // Constructeur avec tous les attributs
  public Module(String code, String name , Semester semester, Filiere filiere) {
    this.code = code;
    this.name = name;
    this.filiere = filiere;
    this.semester = semester;
  }

  // Constructeur avec tous les attributs
  public Module(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Module(String code, String name, String semester, Module filiereCode) {
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

  @Override
  public String toString() {
    return "Module{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", filiere=" + filiere +
            ", semester=" + semester +
            ", moduleElements=" + moduleElements +
            '}';
  }


}