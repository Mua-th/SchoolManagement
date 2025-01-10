package org.example.services.note;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.repositories.note.StudentGradeRepo;
import org.example.repositories.academique.ModuleElementDao;
import org.example.repositories.academique.ModuleElementDaoImpl;
import org.example.zapp.AppState;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentGradeService implements StudentGradeServiceInterface {

  private static StudentGradeService instance;
  private StudentGradeRepo studentGradeRepo ;
  private ModuleElementDao moduleElementDao ;
  private AccessControl accessControl;

  public StudentGradeService(StudentGradeRepo studentGradeRepo, ModuleElementDao moduleElementDao, AccessControl accessControl) {
    this.studentGradeRepo = studentGradeRepo;
    this.moduleElementDao = moduleElementDao;
    this.accessControl = accessControl;
  }

  public static StudentGradeService getInstance(  StudentGradeRepo studentGradeRepo, ModuleElementDao moduleElementDao, AccessControl accessControl) {
    if (instance == null) {
      synchronized (StudentGradeService.class) {
        if (instance == null) {
          instance = new StudentGradeService(studentGradeRepo, moduleElementDao, accessControl) ;
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
    return studentGradeRepo.getStudentGradesByModuleElement(studentId, moduleElementCode);

  }

  @Override
  public double calculateWeightedAverage(List<StudentGrade> studentGrades) {
    double ccGrade = studentGrades.stream()
      .filter(grade -> grade.getStudentGradeId().getEvaluationModality() == EvaluationModality.EXAM)
      .mapToDouble(StudentGrade::getGrade)
      .average()
      .orElse(0.0);

    double tpGrade = studentGrades.stream()
      .filter(grade -> grade.getStudentGradeId().getEvaluationModality() == EvaluationModality.TP)
      .mapToDouble(StudentGrade::getGrade)
      .average()
      .orElse(0.0);

    double projectGrade = studentGrades.stream()
      .filter(grade -> grade.getStudentGradeId().getEvaluationModality() == EvaluationModality.PROJECT)
      .mapToDouble(StudentGrade::getGrade)
      .average()
      .orElse(0.0);

    return (ccGrade * 0.30) + (tpGrade * 0.20) + (projectGrade * 0.50);
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
    //check if the grade is between 0 and 20

    if (studentGrade.getGrade() < 0 || studentGrade.getGrade() > 20) {
      throw new IllegalArgumentException("Grade must be between 0 and 20.");
    }

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
  public void exportGradesToExcel(String moduleElementCode, String filePath) throws Exception {

    String currentUserId = AppState.getInstance().getUser().getId();
    if (!accessControl.hasAccess(currentUserId, moduleElementCode)) {
      throw new Exception("User does not have access to this operation.");
    }

    ModuleElement moduleElement = moduleElementDao.findById(moduleElementCode);
    if (moduleElement == null || !moduleElement.isValidated()) {
      throw new Exception("Module element is not validated.");
    }
    List<StudentGrade> grades = studentGradeRepo.findByModuleElement(moduleElementCode);
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Grades");

    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("Student ID");
    headerRow.createCell(1).setCellValue("Module Element Code");
    headerRow.createCell(2).setCellValue("EXAM Grade");
    headerRow.createCell(3).setCellValue("TP Grade");
    headerRow.createCell(4).setCellValue("PROJECT Grade");
    headerRow.createCell(5).setCellValue("Average Grade");

    Map<String, Map<EvaluationModality, Double>> studentGradesMap = new HashMap<>();

    for (StudentGrade grade : grades) {
      String studentId = grade.getStudentGradeId().getStudentId();
      EvaluationModality modality = grade.getStudentGradeId().getEvaluationModality();
      studentGradesMap
        .computeIfAbsent(studentId, k -> new HashMap<>())
        .put(modality, grade.getGrade());
    }

    int rowNum = 1;
    for (Map.Entry<String, Map<EvaluationModality, Double>> entry : studentGradesMap.entrySet()) {
      String studentId = entry.getKey();
      Map<EvaluationModality, Double> gradesMap = entry.getValue();

      Row row = sheet.createRow(rowNum++);
      row.createCell(0).setCellValue(studentId);
      row.createCell(1).setCellValue(moduleElementCode);
      row.createCell(2).setCellValue(gradesMap.getOrDefault(EvaluationModality.EXAM, 0.0));
      row.createCell(3).setCellValue(gradesMap.getOrDefault(EvaluationModality.TP, 0.0));
      row.createCell(4).setCellValue(gradesMap.getOrDefault(EvaluationModality.PROJECT, 0.0));

      // Calculate the average grade
      double averageGrade = (gradesMap.getOrDefault(EvaluationModality.EXAM, 0.0) * 0.30) +
        (gradesMap.getOrDefault(EvaluationModality.TP, 0.0) * 0.20) +
        (gradesMap.getOrDefault(EvaluationModality.PROJECT, 0.0) * 0.50);
      row.createCell(5).setCellValue(averageGrade);
    }

    if (!filePath.endsWith(".xlsx")) {
      filePath += ".xlsx";
    }

    try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
      workbook.write(outputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    workbook.close();
  }
  @Override
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