package org.example.models.note;

import java.io.Serializable;

public class StudentGradeId implements Serializable {
  private String studentId;
  private String moduleElementCode;
  private String evaluationModality;


  public StudentGradeId(String studentId, String moduleElementCode, String evaluationModality) {
    this.studentId = studentId;
    this.moduleElementCode = moduleElementCode;
    this.evaluationModality = evaluationModality;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getModuleElementCode() {
    return moduleElementCode;
  }

  public void setModuleElementCode(String moduleElementCode) {
    this.moduleElementCode = moduleElementCode;
  }

  public String getEvaluationModality() {
    return evaluationModality;
  }

  public void setEvaluationModality(String evaluationModality) {
    this.evaluationModality = evaluationModality;
  }

  @Override
  public String toString() {
    return "StudentGradeId{" +
      "studentId='" + studentId + '\'' +
      ", moduleElementCode='" + moduleElementCode + '\'' +
      ", evaluationModality='" + evaluationModality + '\'' +
      '}';
  }
}