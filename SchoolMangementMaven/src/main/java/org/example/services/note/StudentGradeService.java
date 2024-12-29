package org.example.services.note;

import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.repositories.StudentGradeRepo;

import java.sql.SQLException;
import java.util.List;

public class StudentGradeService implements StudentGradeServiceInterface {

  private static StudentGradeService instance;
  private StudentGradeRepo studentGradeRepo = StudentGradeRepo.getInstance();

  private StudentGradeService() {
  }

  public static StudentGradeService getInstance() {
    if (instance == null) {
      synchronized (StudentGradeService.class) {
        if (instance == null) {
          instance = new StudentGradeService();
        }
      }
    }
    return instance;
  }

  public List<StudentGrade> findByModuleElement(String moduleElementCode) throws SQLException {
    return studentGradeRepo.findByModuleElement(moduleElementCode);
  }

  @Override
  public List<StudentGrade> getStudentGrades(String studentId) throws SQLException {

    return studentGradeRepo.getStudentGrades(studentId);

  }

  @Override
  public List<StudentGrade> getStudentGradesByModuleElement(String studentId, String moduleElementCode) throws SQLException {
    return null;
  }


  @Override
  public List<StudentGrade> getStudentGradesByModuleElementAndModality(String studentId, String moduleElementCode ,EvaluationModality evaluationModality) throws SQLException {
    return studentGradeRepo.getStudentGradesByModuleElementAndModality(studentId, moduleElementCode , evaluationModality);
  }
  //implement the save method

  @Override
  public StudentGrade findById(StudentGradeId studentGradeId) throws SQLException {


    return StudentGradeRepo.getInstance().findById(studentGradeId);
  }

  @Override
  public List<StudentGrade> findAll() {
    return null;
  }

  public boolean save(StudentGrade studentGrade) throws SQLException {
    return studentGradeRepo.save(studentGrade);
  }

  @Override
  public boolean delete(StudentGradeId studentGradeId) {
    return false;
  }


  public boolean saveForAllModalities(String studentId, String moduleElementCode, double examGrade , double TpGrade , double projectGrade) throws SQLException {

    StudentGrade examStudentGrade = new StudentGradeBuilder().studentGradeId(
      new StudentGradeId(studentId, moduleElementCode, EvaluationModality.EXAM)).grade(examGrade).build();

    StudentGrade tpStudentGrade = new StudentGradeBuilder().studentGradeId(
      new StudentGradeId(studentId, moduleElementCode, EvaluationModality.TP)).grade(TpGrade).build();

    StudentGrade projectStudentGrade = new StudentGradeBuilder().studentGradeId(
      new StudentGradeId(studentId , moduleElementCode , EvaluationModality.PROJECT)).grade(projectGrade).build();

    if (save(examStudentGrade) && save(tpStudentGrade) && save(projectStudentGrade)) {


      return true ;}
    return false;
}
}