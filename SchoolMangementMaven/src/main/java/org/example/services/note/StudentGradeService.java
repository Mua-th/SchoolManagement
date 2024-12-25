package org.example.services.note;

import org.example.models.note.StudentGrade;
import org.example.repositories.StudentGradeRepo;

import java.sql.SQLException;
import java.util.List;

public class StudentGradeService {

  private StudentGradeRepo studentGradeRepo = StudentGradeRepo.getInstance();

  public List<StudentGrade> findByModuleElement(String moduleElementCode) throws SQLException {
    return studentGradeRepo.findByModuleElement(moduleElementCode);
  }
}
