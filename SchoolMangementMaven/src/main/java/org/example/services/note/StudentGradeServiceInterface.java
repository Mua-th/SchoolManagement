package org.example.services.note;

import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.services.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentGradeServiceInterface extends Service<StudentGrade, StudentGradeId> {

  public List<StudentGrade> findByModuleElement(String moduleElementCode) throws SQLException;
  public  List<StudentGrade> getStudentGrades(String studentId) throws SQLException;

  List<StudentGrade> getStudentGradesByModuleElement(String studentId, String moduleElementCode) throws SQLException;

  double calculateWeightedAverage(List<StudentGrade> studentGrades);

  List<StudentGrade> getStudentGradesByModuleElementAndModality(String studentId, String moduleElementCode, EvaluationModality evaluationModality) throws SQLException;

  boolean updateStudentGrade(StudentGrade studentGrade) throws SQLException;

  void exportGradesToExcel(String moduleElementCode, String filePath) throws SQLException, IOException, IOException;

  boolean saveForAllModalities(String studentId, String moduleElementCode, double examGrade, double TpGrade, double projectGrade) throws SQLException;
}
