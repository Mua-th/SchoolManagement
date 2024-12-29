package org.example.controllers;

import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.models.users.Student.Student;
import org.example.repositories.StudentGradeRepo;
import org.example.services.academique.ModuleElementService;
import org.example.services.academique.ModuleElementServiceImpl;
import org.example.services.note.StudentGradeService;
import org.example.services.user.StudentService;
import org.example.zapp.AppState;

import org.example.zapp.vue.Prof.ViewProf;
import org.example.zapp.vue.Prof.ViewProfInterface;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProfController {

  private final ModuleElementService moduleElementService;
  private final StudentGradeService studentGradeService ;

  private ViewProfInterface viewProf ;
  LogoutController logoutController;

  StudentService studentService ;


  public ProfController() {
    this.moduleElementService = new ModuleElementServiceImpl();
    this.studentGradeService = StudentGradeService.getInstance();
    logoutController = new LogoutController(AppState.getInstance());

  }


  public ProfController(ModuleElementService moduleElementService, StudentGradeService studentGradeService, StudentService studentService, AppState appState, ViewProfInterface viewProf) {
    this.moduleElementService = moduleElementService;
    this.studentGradeService = studentGradeService;
    this.studentService = studentService;
    this.logoutController = new LogoutController(appState);
    this.viewProf = viewProf ;
  }



  public void handleInput() {
   // Scanner scanner = new Scanner(System.in);

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
      default:
        viewProf.displayMessage("Invalid choice. Please try again.");
    }
  }

  public Student handleFindStudentById() {
    try {
      String studentId = viewProf.findStudentByIdForm();
     // System.out.println("studentId: " + studentId + "profId: " + AppState.getUser().getId());
      Student student = studentService.findByIdForProf(AppState.getUser().getId() , studentId);
      if (student != null) {
        viewProf.displayStudent(student);
        return student;
      } else {
        viewProf.displayMessage("Student not found.");
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

  }

  public void handleViewModuleElements() {
    try {
      String professorId = AppState.getUser().getId();
      List<ModuleElement> moduleElements = moduleElementService.getModuleElementsByProfId(professorId);
       viewProf.displayModuleElements(moduleElements);
      ModuleElement selectedmoduleElement = viewProf.handleModuleElementSelection(moduleElements);
      List<Student> students = moduleElementService.getSubscribedStudents(selectedmoduleElement);

      Map<Student,List<StudentGrade>> studentGrades = new HashMap<>() ;
      for (Student student : students) {
        studentGrades.put(student, studentGradeService.getStudentGradesByModuleElement(student.getId(), selectedmoduleElement.getCode()));
      }

      viewProf.displaySubscribedStudentsGrades(selectedmoduleElement, studentGrades);


      Student selectedStudent = viewProf.handleSubscribedStudentSelection(students)    ;

     StudentGrade studentGrade = studentGradeService.findById(new StudentGradeId(selectedStudent.getId() , selectedmoduleElement.getCode()  , EvaluationModality.EXAM));
      viewProf.displayStudentGrade(studentGrade);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }



  public void handleInsertStudentGrade() {
    try {

      viewProf.displayModuleElements(moduleElementService.getModuleElementsByProfId(AppState.getUser().getId()));
      ModuleElement selectedModuleElement = viewProf.handleModuleElementSelection(moduleElementService.getModuleElementsByProfId(AppState.getUser().getId()));
      EvaluationModality evaluationModality = viewProf.getChosenModality();
      List<StudentGrade> studentGrades = viewProf.handleInserStudentGradeForModality(selectedModuleElement, moduleElementService.getSubscribedStudents(selectedModuleElement), evaluationModality);
      for (StudentGrade studentGrade : studentGrades) {
       if( studentGradeService.save(studentGrade)){
        viewProf.displayMessage("Student grade saved successfully.");
        }
       else {
        viewProf.displayMessage("Failed to save student grade.");
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}