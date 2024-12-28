package org.example.services.note;

import org.example.models.note.StudentGrade;
import org.example.repositories.StudentGradeRepo;

import java.sql.SQLException;
import java.util.List;

public class StudentGradeService {

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
  //implement the save method

public boolean save(StudentGrade studentGrade) throws SQLException {
    return studentGradeRepo.save(studentGrade);
  }


}