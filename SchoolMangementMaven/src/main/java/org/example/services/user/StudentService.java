package org.example.services.user;

import org.example.models.users.Student.Student;
import org.example.services.Service;

import java.sql.SQLException;

public interface StudentService extends Service<Student, String> {
  Student findByIdForProf(String professorId, String studentId) throws SQLException;

}
