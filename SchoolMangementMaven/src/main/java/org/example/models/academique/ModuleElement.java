package org.example.models.academique;


import org.example.models.note.EvaluationModality;
import org.example.models.users.Prof.Professor;

public class ModuleElement {
  private String code;
  private Module parentModule;
  private double coefficient;

  private boolean isValidated;
  private Professor responsibleProfessor;
  private EvaluationModality evaluationModality;

  public boolean isValidated() {
    return isValidated;
  }

  public ModuleElement() {
  }

  public void setValidated(boolean validated) {
    isValidated = validated;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Module getParentModule() {
    return parentModule;
  }

  public void setParentModule(Module parentModule) {
    this.parentModule = parentModule;
  }

  public double getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(double coefficient) {
    this.coefficient = coefficient;
  }

  public Professor getResponsibleProfessor() {
    return responsibleProfessor;
  }

  public void setResponsibleProfessor(Professor responsibleProfessor) {
    this.responsibleProfessor = responsibleProfessor;
  }

  public EvaluationModality getEvaluationModality() {
    return evaluationModality;
  }

  public void setEvaluationModality(EvaluationModality evaluationModality) {
    this.evaluationModality = evaluationModality;
  }

  public ModuleElement(String code, double coefficient , boolean isValidated, Module parentModule) {
    this.code = code;
    this.coefficient = coefficient;
    this.parentModule = parentModule;
    this.isValidated = isValidated;
  }
}