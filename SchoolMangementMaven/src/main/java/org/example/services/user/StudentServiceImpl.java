package org.example.services.user;

import org.example.models.users.Student.Student;
import org.example.repositories.User.StudentDAO;
import org.example.services.Service;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {

  //make it singleton

  private static StudentServiceImpl instance;

  public static StudentServiceImpl getInstance(StudentDAO studentDao){
    if(instance==null){
      instance = new StudentServiceImpl(studentDao);
    }
    return instance;
  }

  //inject StudentDAO
  StudentDAO studentDao;

  private  StudentServiceImpl(StudentDAO studentDao) {
    this.studentDao = studentDao;
  }


  @Override
  public Student findById(String s) throws SQLException {
    return null;
  }

  @Override
  public List<Student> findAll() {
    return null;
  }

  @Override
  public boolean save(Student student) throws SQLException {
    return false;
  }

  @Override
  public boolean delete(String s) {
    return false;
  }

  @Override
  public Student findByIdForProf(String professorId, String studentId) throws SQLException {
    return studentDao.findByIdForProf(professorId, studentId);
  }
}
