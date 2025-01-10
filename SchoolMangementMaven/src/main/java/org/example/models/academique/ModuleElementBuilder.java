package org.example.models.academique;


import org.example.models.note.EvaluationModality;
import org.example.models.users.Prof.Professor;

public class ModuleElementBuilder {
  private String code;
  private Module parentModule;
  private double coefficient;
  private boolean isValidated;
  private Professor responsibleProfessor;
  private EvaluationModality evaluationModality;

  public ModuleElementBuilder setCode(String code) {
    this.code = code;
    return this;
  }

  public ModuleElementBuilder setParentModule(Module parentModule) {
    this.parentModule = parentModule;
    return this;
  }

  public ModuleElementBuilder setCoefficient(double coefficient) {
    this.coefficient = coefficient;
    return this;
  }

  public ModuleElementBuilder setValidated(boolean isValidated) {
    this.isValidated = isValidated;
    return this;
  }

  public ModuleElementBuilder setResponsibleProfessor(Professor responsibleProfessor) {
    this.responsibleProfessor = responsibleProfessor;
    return this;
  }

  public ModuleElementBuilder setEvaluationModality(EvaluationModality evaluationModality) {
    this.evaluationModality = evaluationModality;
    return this;
  }

  public ModuleElement build() {
    ModuleElement moduleElement = new ModuleElement();
    moduleElement.setCode(this.code);
    moduleElement.setParentModule(this.parentModule);
    moduleElement.setCoefficient(this.coefficient);
    moduleElement.setValidated(this.isValidated);
    moduleElement.setResponsibleProfessor(this.responsibleProfessor);
    moduleElement.setEvaluationModality(this.evaluationModality);
    return moduleElement;
  }
}