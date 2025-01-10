package org.example.services.user;

import org.example.models.users.Student.Student;
import org.example.services.Service;

import java.sql.SQLException;
import java.util.Optional;

public interface StudentService extends Service<Student, String> {
  Student findByIdForProf(String professorId, String studentId) throws SQLException;
  void updateStudent(Student student) throws SQLException;
  Student findStudentByLastName(String lastName) throws SQLException;


}
