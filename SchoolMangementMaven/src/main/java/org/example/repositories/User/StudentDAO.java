package org.example.repositories.User;

import org.example.models.users.Student.Student;
import org.example.repositories.Repository;

import java.sql.SQLException;

public interface StudentDAO extends Repository<Student, String> {
  Student findByIdForProf(String professorId, String studentId) throws SQLException;


}
