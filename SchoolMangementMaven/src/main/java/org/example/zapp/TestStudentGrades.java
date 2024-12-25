package org.example.zapp;

import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.repositories.StudentGradeRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestStudentGrades {

  public static void main(String[] args) throws SQLException {
    StudentGradeRepo studentGradeRepo = StudentGradeRepo.getInstance();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Choose an operation:");
      System.out.println("1. Get student grades");
      System.out.println("2. Save student grade");
      System.out.println("3. Find grades by module element");
      System.out.println("4. Exit");

      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          System.out.print("Enter student ID: ");
          String studentId = scanner.nextLine();
          List<StudentGrade> studentGrades = studentGradeRepo.getStudentGrades(studentId);
          for (StudentGrade grade : studentGrades) {
            System.out.println("Module: " + grade.getStudentGradeId().getModuleElementCode() +
              ", Modality: " + grade.getStudentGradeId().getEvaluationModality() +
              ", Grade: " + grade.getGrade());
          }
          break;
        case 2:
          System.out.print("Enter student ID: ");
          String newStudentId = scanner.nextLine();
          System.out.print("Enter module element code: ");
          String moduleElementCode = scanner.nextLine();
          System.out.print("Enter modality: ");
          String modality = scanner.nextLine();
          System.out.print("Enter grade: ");
          Double newgrade = scanner.nextDouble();
          scanner.nextLine(); // Consume newline

          StudentGrade newStudentGrade = new StudentGradeBuilder()
            .studentGradeId(new StudentGradeId(newStudentId, moduleElementCode, modality))
            .grade(newgrade)
            .build();

          boolean isSaved = studentGradeRepo.save(newStudentGrade);
          if (isSaved) {
            System.out.println("Student grade saved successfully.");
          } else {
            System.out.println("Failed to save student grade.");
          }
          break;
        case 3:
          System.out.print("Enter module element code: ");
          String moduleCode = scanner.nextLine();
          List<StudentGrade> moduleGrades = studentGradeRepo.findByModuleElement(moduleCode);
          for (StudentGrade grade : moduleGrades) {
            System.out.println("Student ID: " + grade.getStudentGradeId().getStudentId() +
              ", Modality: " + grade.getStudentGradeId().getEvaluationModality() +
              ", Grade: " + grade.getGrade());
          }
          break;
        case 4:
          System.out.println("Exiting...");
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    }
  }
}