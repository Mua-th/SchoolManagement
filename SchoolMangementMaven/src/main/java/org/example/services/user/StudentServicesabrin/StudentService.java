package org.example.services.user.StudentServicesabrin;

import org.example.models.academique.Filiere;
import org.example.models.users.Student.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    boolean addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(String id) throws SQLException;
    Optional<Student> findStudentByLastName(String lastName) throws SQLException;
    List<Student> getAllStudents() throws SQLException;
    Filiere getFiliereByCode(String filiereCode) throws SQLException;
}
