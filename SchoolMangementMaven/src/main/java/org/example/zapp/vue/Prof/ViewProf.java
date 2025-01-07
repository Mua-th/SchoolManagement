package org.example.zapp.vue.Prof;

import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.models.users.Student.Student;
import org.example.zapp.AppState;
import org.example.zapp.vue.View;

import java.util.*;

public class ViewProf extends View  implements ViewProfInterface  , Observer {

  private static ViewProf instance;

  private ViewProf() {
  }

  public static ViewProf getInstance() {
    if (instance == null) {
      synchronized (ViewProf.class) {
        if (instance == null) {
          instance = new ViewProf();
        }
      }
    }
    return instance;
  }

  @Override
  public void displayMenuProf() {
    renderMenu(Arrays.asList("View Module Elements", "Insert Student Grades" , "FindStudentById", "Logout" , "Valider les notes d'un élément" , "Exporter les notes d'un élément"));
  }

  @Override
  public String getUserChoice() {
    System.out.print("Enter your choice: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public StudentGrade insertStudentGradeForm() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter student ID: ");
    String studentId = scanner.nextLine();
    System.out.print("Enter module element code: ");
    String moduleElementCode = scanner.nextLine();
    System.out.print("Enter modality: ");
    String modality = scanner.nextLine();
    System.out.print("Enter grade: ");
    Double grade = scanner.nextDouble();
    scanner.nextLine() ;

    return new StudentGradeBuilder()
      .studentGradeId(new StudentGradeId(studentId, moduleElementCode, EvaluationModality.valueOf(modality)))
      .grade(grade)
      .build();
  }

  @Override
  public void displayModuleElements(List<ModuleElement> moduleElements) {
    System.out.println("Module Elements for Professor " + AppState.getUser().getFirstName() + ":");
    for (int i = 0; i < moduleElements.size(); i++) {
      ModuleElement moduleElement = moduleElements.get(i);
      System.out.println((i + 1) + ". Code: " + moduleElement.getCode() + ", Coefficient: " + moduleElement.getCoefficient());
    }
  }

  @Override
  public String findStudentByIdForm() {

    System.out.print("Enter student ID: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public void displayStudent(Student student) {
    System.out.println("Student ID: " + student.getId());
    System.out.println("First Name: " + student.getFirstName());
    System.out.println("Last Name: " + student.getLastName());
  }


  @Override
  public ModuleElement handleModuleElementSelection(List<ModuleElement> moduleElements) {
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (choice > 0 && choice <= moduleElements.size()) {
      ModuleElement selectedModuleElement = moduleElements.get(choice - 1);
      return selectedModuleElement ;
      //displaySubscribedStudents(selectedModuleElement);
    } else {
      System.out.println("Invalid choice. Please try again.");
    }



    return null ;
  }

  @Override
  public void displaySubscribedStudentsGrades(ModuleElement moduleElement, Map<Student, List<StudentGrade>> studentListMap) {
    System.out.println("Subscribed Students for Module Element " + moduleElement.getCode() + ":");
    for (Map.Entry<Student, List<StudentGrade>> entry : studentListMap.entrySet()) {
      Student student = entry.getKey();
      System.out.print("\033[1;34mStudent ID: " + student.getId() + "\033[0m, \033[1;32mFirst Name: " + student.getFirstName() + "\033[0m, \033[1;32mLast Name: " + student.getLastName() + "\033[0m");

      Map<EvaluationModality, Double> gradesMap = new HashMap<>();
      for (StudentGrade studentGrade : entry.getValue()) {
        gradesMap.put(studentGrade.getStudentGradeId().getEvaluationModality(), studentGrade.getGrade());
      }

      for (EvaluationModality modality : EvaluationModality.values()) {
        Double grade = gradesMap.get(modality);
        System.out.print(", \033[1;33m" + modality + " Grade: " + (grade != null ? grade : "N/A") + "\033[0m");
      }
      System.out.println();
    }
  }

  @Override
  public void displayAverageGrade(ModuleElement moduleElement, double averageGrade) {
    System.out.println("Average grade for Module Element " + moduleElement.getCode() + ": " + averageGrade);
  }


  @Override
  public Student handleSubscribedStudentSelection(List<Student> students){
    Scanner scanner = new Scanner(System.in);

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (choice > 0 && choice <= students.size()) {
      Student selectedstudent = students.get(choice - 1);
      return selectedstudent ;
    } else {
      System.out.println("Invalid choice. Please try again.");
    }

    return null ;
  }

  @Override
  public void displayStudentGrade(StudentGrade studentGrade) {
    if (studentGrade != null) {
      System.out.println("Student ID: " + studentGrade.getStudentGradeId().getStudentId());
      System.out.println("Module Element Code: " + studentGrade.getStudentGradeId().getModuleElementCode());
      System.out.println("Modality: " + studentGrade.getStudentGradeId().getEvaluationModality());
      System.out.println("Grade: " + studentGrade.getGrade());
    } else {
      System.out.println("Student grade not found.");
    }
  }

  @Override
  public EvaluationModality getChosenModality() {

    System.out.print("Enter modality: EXAM , TP, PROJECT :");
    Scanner scanner = new Scanner(System.in);

    return EvaluationModality.valueOf(scanner.nextLine().toUpperCase());
  }

  @Override
  public List<StudentGrade> handleInserStudentGradeForModality(ModuleElement selectedModuleElement, List<Student> subscribedStudents, EvaluationModality evaluationModality) {
    System.out.println("Subscribed Students for Module Element " + selectedModuleElement.getCode() + ":");
    Scanner scanner = new Scanner(System.in);
    List<StudentGrade> studentGrades = new ArrayList<>();
    for (int i = 0; i < subscribedStudents.size(); i++) {
      Student student = subscribedStudents.get(i);
      System.out.println((i + 1) + ". Student ID: " + student.getId() + ", First Name: " + student.getFirstName() + ", Last Name: " + student.getLastName());
      System.out.print("Enter grade: ");
      studentGrades.add(new StudentGradeBuilder()
        .studentGradeId(new StudentGradeId(student.getId(), selectedModuleElement.getCode(), evaluationModality))
        .grade(scanner.nextDouble())
        .build());

    }
    return studentGrades;
  }


  @Override
  public String getModuleElementCodeForValidation() {
    System.out.print("Enter module element code to validate: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public String getModuleElementCodeForExport() {
    System.out.print("Enter module element code to export grades: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public String getFilePathForExport() {
    System.out.print("Enter file path to export grades: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  @Override
  public void update(Observable o, Object arg) {
    displayMenuProf();
  }

  // Dummy method to simulate fetching students by module element


}