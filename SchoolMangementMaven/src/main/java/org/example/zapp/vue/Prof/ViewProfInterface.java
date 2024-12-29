package org.example.zapp.vue.Prof;

import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.users.Student.Student;
import org.example.zapp.vue.ViewInterface;

import java.util.List;
import java.util.Map;

public interface ViewProfInterface extends ViewInterface {
  void displayMenuProf();
  String getUserChoice();
  StudentGrade insertStudentGradeForm();

  public ModuleElement handleModuleElementSelection(List<ModuleElement> moduleElements) ;


 // public void displaySubscribedStudents(ModuleElement moduleElement , List<Student> students) ;


  void displayModuleElements(List<ModuleElement> moduleElements);

  String findStudentByIdForm();

  void displayStudent(Student student);


  void displaySubscribedStudentsGrades(ModuleElement moduleElement, Map<Student, List<StudentGrade>> studentListMap);

  Student handleSubscribedStudentSelection(List<Student> students);

  void displayStudentGrade(StudentGrade studentGrade);

  EvaluationModality getChosenModality();

  List<StudentGrade> handleInserStudentGradeForModality(ModuleElement selectedModuleElement, List<Student> subscribedStudents, EvaluationModality evaluationModality);
}