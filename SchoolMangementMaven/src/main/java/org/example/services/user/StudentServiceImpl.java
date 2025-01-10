package org.example.services.user;

import org.example.models.users.Student.Student;
import org.example.repositories.User.StudentDAO;
import org.example.services.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    return studentDao.findById(s);
  }

  @Override
  public List<Student> findAll() throws SQLException {
    return studentDao.findAll();
  }

  @Override
  public boolean save(Student student) throws SQLException {
    return studentDao.save(student);
  }

  @Override
  public boolean delete(String s) throws SQLException {
    return  studentDao.delete(s);
  }

  @Override
  public Student findByIdForProf(String professorId, String studentId) throws SQLException {
    return studentDao.findByIdForProf(professorId, studentId);
  }

  @Override
  public void updateStudent(Student student) throws SQLException {
    studentDao.updateStudent(student);

  }

  @Override
  public Student findStudentByLastName(String lastName) throws SQLException {
    return studentDao.findStudentByLastName(lastName);
  }
}
