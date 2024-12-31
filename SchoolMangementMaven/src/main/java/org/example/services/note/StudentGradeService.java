package org.example.services.note;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.repositories.StudentGradeRepo;
import org.example.repositories.academique.ModuleElementDao;
import org.example.repositories.academique.ModuleElementDaoImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentGradeService implements StudentGradeServiceInterface {

  private static StudentGradeService instance;
  private StudentGradeRepo studentGradeRepo = StudentGradeRepo.getInstance();

  ModuleElementDao moduleElementDao = ModuleElementDaoImpl.getInstance();
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


  @Override
  public boolean updateStudentGrade(StudentGrade studentGrade) throws SQLException {

    ModuleElement moduleElement = moduleElementDao.findById(studentGrade.getStudentGradeId().getModuleElementCode());
    if (moduleElement != null && !moduleElement.isValidated()) {
      return studentGradeRepo.save(studentGrade);
    }
    return false;
  }
  @Override
  public void exportGradesToExcel(String moduleElementCode, String filePath) throws SQLException, IOException {

    //check if the module element is validated

    ModuleElement moduleElement = moduleElementDao.findById(moduleElementCode);
    if (moduleElement == null || !moduleElement.isValidated()) {
      throw new IllegalArgumentException("Module element is not validated.");
    }

    List<StudentGrade> grades = studentGradeRepo.findByModuleElement(moduleElementCode);
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Grades");

    int rowNum = 0;
    for (StudentGrade grade : grades) {
      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(grade.getStudentGradeId().getStudentId());
      row.createCell(1).setCellValue(grade.getStudentGradeId().getModuleElementCode());
      row.createCell(2).setCellValue(grade.getStudentGradeId().getEvaluationModality().name());
      row.createCell(3).setCellValue(grade.getGrade());
    }

    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
      workbook.write(outputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    workbook.close();
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