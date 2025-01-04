package org.example.controllers;

import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.models.users.Student.Student;
import org.example.services.academique.ModuleElementService;
import org.example.services.note.StudentGradeService;
import org.example.services.user.StudentService;
import org.example.zapp.AppState;
import org.example.zapp.vue.Prof.ViewProfInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ProfController implements Observer {

  private final ModuleElementService moduleElementService;
  private final StudentGradeService studentGradeService;
  private final StudentService studentService;
  private final ViewProfInterface viewProf;
  private final LogoutController logoutController;

  public ProfController(ModuleElementService moduleElementService, StudentGradeService studentGradeService, StudentService studentService, AppState appState, ViewProfInterface viewProf) {
    this.moduleElementService = moduleElementService;
    this.studentGradeService = studentGradeService;
    this.studentService = studentService;
    this.logoutController = new LogoutController(appState);
    this.viewProf = viewProf;
  }

  public void handleInput() {
    String choice = viewProf.getUserChoice();

    switch (choice) {
      case "1":
        handleViewModuleElements();
        break;
      case "2":
        handleInsertStudentGrade();
        break;
      case "3":
        handleFindStudentById();
        break;
      case "4":
        logoutController.handleLogout();
        break;
      case "5":
        handleValidateModuleElement();
        break;
      case "6":
        handleExportGrades();
        break;
      default:
        viewProf.displayMessage("Invalid choice. Please try again.");
    }
  }

  public void handleValidateModuleElement() {
    try {
      String moduleElementCode = viewProf.getModuleElementCodeForValidation();
      boolean isValidated = moduleElementService.validateModuleElement(moduleElementCode);
      if (isValidated) {
        viewProf.displayMessage("Module element validated successfully.");
      } else {
        viewProf.displayMessage("Failed to validate module element.");
      }
    }  catch (Exception e) {
      viewProf.displayMessage(e.getMessage());
    }
  }

  public void handleExportGrades() {
    try {
      String moduleElementCode = viewProf.getModuleElementCodeForExport();
      String filePath = viewProf.getFilePathForExport();
      studentGradeService.exportGradesToExcel(moduleElementCode, filePath);
      viewProf.displayMessage("Grades exported successfully.");
    } catch (SQLException | IOException e) {
      e.printStackTrace();
      viewProf.displayMessage("Failed to export grades.");
    }
  }

  public void handleFindStudentById() {
    try {
      String studentId = viewProf.findStudentByIdForm();
      Student student = studentService.findByIdForProf(AppState.getUser().getId(), studentId);
      if (student != null) {
        viewProf.displayStudent(student);
        List<ModuleElement> moduleElements = moduleElementService.getModuleElementsByProfId(AppState.getUser().getId());
        for (ModuleElement moduleElement : moduleElements) {
          List<StudentGrade> studentGrades = studentGradeService.getStudentGradesByModuleElement(studentId, moduleElement.getCode());
          double averageGrade = studentGradeService.calculateWeightedAverage(studentGrades);
          viewProf.displayAverageGrade(moduleElement, averageGrade);
        }
      } else {
        viewProf.displayMessage("Student not found.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  public void handleViewModuleElements() {
    try {
      String professorId = AppState.getUser().getId();
      List<ModuleElement> moduleElements = moduleElementService.getModuleElementsByProfId(professorId);
      viewProf.displayModuleElements(moduleElements);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void handleInsertStudentGrade() {
    try {
      viewProf.displayModuleElements(moduleElementService.getModuleElementsByProfId(AppState.getUser().getId()));
      ModuleElement selectedModuleElement = viewProf.handleModuleElementSelection(moduleElementService.getModuleElementsByProfId(AppState.getUser().getId()));
      EvaluationModality evaluationModality = viewProf.getChosenModality();
      List<StudentGrade> studentGrades = viewProf.handleInserStudentGradeForModality(
        selectedModuleElement,
        moduleElementService.getSubscribedStudents(selectedModuleElement),
        evaluationModality);

      for (StudentGrade studentGrade : studentGrades) {
        studentGradeService.save(studentGrade);
      }
      viewProf.displayMessage("Student grades inserted successfully.");
    } catch (Exception e) {
     viewProf.displayMessage(e.getMessage());
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof String) {
      String action = (String) arg;
      switch (action) {
        case "viewModuleElements":
          handleViewModuleElements();
          break;
        case "insertStudentGrades":
          handleInsertStudentGrade();
          break;
        case "findStudentById":
          handleFindStudentById();
          break;
        case "logout":
          logoutController.handleLogout();
          break;
        default:
          viewProf.displayMessage("Unknown action: " + action);
      }
    }
  }
}