package org.example.services.user.StudentService;

import org.example.models.academique.Filiere;
import org.example.models.user.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    void addStudent(Student student);
    void updateStudent(String id, String firstName, String lastName);
    void deleteStudent(String id);
    Optional<Student> findStudentByLastName(String lastName);
    List<Student> getAllStudents();
    Filiere getFiliereByCode(String filiereCode);
}
