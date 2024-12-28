package org.example.zapp.vue.Prof;

import org.example.models.academique.ModuleElement;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.models.users.Student.Student;
import org.example.zapp.AppState;
import org.example.zapp.vue.View;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ViewProf extends View  implements ViewProfInterface {

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
    renderMenu(Arrays.asList("View Module Elements", "Insert Student Grades" , "FindStudentById", "Logout"));
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
      .studentGradeId(new StudentGradeId(studentId, moduleElementCode, modality))
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

    System.out.print("Enter the number of the module element to view subscribed students: ");
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
  public void displaySubscribedStudents(ModuleElement moduleElement , List<Student> students) {
    // Assuming you have a method to get students by module element

    System.out.println("Students subscribed to module element " + moduleElement.getCode() + ":");
    for (Student student : students) {
      System.out.println("ID: " + student.getId() + ", Name: " + student.getFirstName() + " " + student.getLastName());
    }
  }

  // Dummy method to simulate fetching students by module element


}