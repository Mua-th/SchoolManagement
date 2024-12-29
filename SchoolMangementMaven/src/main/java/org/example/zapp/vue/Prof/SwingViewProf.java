package org.example.zapp.vue.Prof;

import org.example.controllers.LogoutController;
import org.example.controllers.ProfController;
import org.example.models.academique.ModuleElement;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeBuilder;
import org.example.models.note.StudentGradeId;
import org.example.models.users.Student.Student;
import org.example.zapp.AppState;
import org.example.zapp.vue.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SwingViewProf implements ViewProfInterface {
  private JPanel panel;
  private JTextArea textArea;
  private JTextField inputField;
  private JButton viewModuleElementsButton;
  private JButton insertStudentGradesButton;
  private JButton findStudentByIdButton;
  private JButton logoutButton;
  private static SwingViewProf instance;

  private LogoutController logoutController;

  public SwingViewProf() {
    panel = new JPanel();
    panel.setLayout(new BorderLayout());

    textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setFont(new Font("Arial", Font.PLAIN, 14));
    textArea.setMargin(new Insets(10, 10, 10, 10));
    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

    inputField = new JTextField();
    inputField.setFont(new Font("Arial", Font.PLAIN, 14));
    panel.add(inputField, BorderLayout.SOUTH);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 4));

    viewModuleElementsButton = new JButton("View Module Elements");
    insertStudentGradesButton = new JButton("Insert Student Grades");
    findStudentByIdButton = new JButton("Find Student By ID");
    logoutButton = new JButton("Logout");

    buttonPanel.add(viewModuleElementsButton);
    buttonPanel.add(insertStudentGradesButton);
    buttonPanel.add(findStudentByIdButton);
    buttonPanel.add(logoutButton);

    panel.add(buttonPanel, BorderLayout.NORTH);

    viewModuleElementsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    insertStudentGradesButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        }
    });

    findStudentByIdButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    logoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });
  }

  public static SwingViewProf getInstance() {
    if (instance == null) {
      synchronized (SwingViewProf.class) {
        if (instance == null) {
          instance = new SwingViewProf();
        }
      }
    }
    return instance;
  }


  @Override
  public void displayMenuProf() {
    MainFrame.getInstance().setPanel(panel);
  }

  @Override
  public String getUserChoice() {
    return JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter your choice:");
  }

  @Override
  public StudentGrade insertStudentGradeForm() {
    String studentId = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter Student ID:");
    String moduleElementCode = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter Module Element Code:");
    String modality = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter Modality:");
    String gradeStr = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter Grade:");
    double grade = Double.parseDouble(gradeStr);

    return new StudentGradeBuilder()
      .studentGradeId(new StudentGradeId(studentId, moduleElementCode, EvaluationModality.valueOf(modality)))
      .grade(grade)
      .build();
  }

  @Override
  public ModuleElement handleModuleElementSelection(List<ModuleElement> moduleElements) {
    String[] options = moduleElements.stream().map(ModuleElement::getCode).toArray(String[]::new);
    int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Select a Module Element:", "Module Elements",
      JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    return moduleElements.get(choice);
  }

  @Override
  public void displayModuleElements(List<ModuleElement> moduleElements) {
    StringBuilder sb = new StringBuilder("Module Elements:\n");
    for (ModuleElement moduleElement : moduleElements) {
      sb.append(moduleElement.getCode()).append("\n");
    }
    textArea.setText(sb.toString());
  }

  @Override
  public String findStudentByIdForm() {
    return JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter Student ID:");
  }

  @Override
  public void displayStudent(Student student) {
    textArea.setText("Student Details:\n" +
      "ID: " + student.getId() + "\n" +
      "First Name: " + student.getFirstName() + "\n" +
      "Last Name: " + student.getLastName() + "\n");
  }

  @Override
  public void displaySubscribedStudentsGrades(ModuleElement moduleElement, Map<Student, List<StudentGrade>> studentListMap) {
    StringBuilder sb = new StringBuilder("Subscribed Students for Module Element " + moduleElement.getCode() + ":\n");
    for (Map.Entry<Student, List<StudentGrade>> entry : studentListMap.entrySet()) {
      Student student = entry.getKey();
      sb.append("Student ID: ").append(student.getId()).append(", First Name: ").append(student.getFirstName()).append(", Last Name: ").append(student.getLastName());

      Map<EvaluationModality, Double> gradesMap = new HashMap<>();
      for (StudentGrade studentGrade : entry.getValue()) {
        gradesMap.put(studentGrade.getStudentGradeId().getEvaluationModality(), studentGrade.getGrade());
      }

      for (EvaluationModality modality : EvaluationModality.values()) {
        Double grade = gradesMap.get(modality);
        sb.append(", ").append(modality).append(" Grade: ").append(grade != null ? grade : "N/A");
      }
      sb.append("\n");
    }
    textArea.setText(sb.toString());
  }

  @Override
  public Student handleSubscribedStudentSelection(List<Student> students) {
    String[] options = students.stream().map(student -> student.getFirstName() + " " + student.getLastName()).toArray(String[]::new);
    int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Select a Student:", "Students",
      JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    return students.get(choice);
  }

  @Override
  public void displayStudentGrade(StudentGrade studentGrade) {
    if (studentGrade != null) {
      textArea.setText("Student Grade Details:\n" +
        "Student ID: " + studentGrade.getStudentGradeId().getStudentId() + "\n" +
        "Module Element Code: " + studentGrade.getStudentGradeId().getModuleElementCode() + "\n" +
        "Modality: " + studentGrade.getStudentGradeId().getEvaluationModality() + "\n" +
        "Grade: " + studentGrade.getGrade() + "\n");
    } else {
      textArea.setText("Student grade not found.");
    }
  }

  @Override
  public EvaluationModality getChosenModality() {
    String[] options = {"EXAM", "TP", "PROJECT"};
    int choice = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Select a Modality:", "Modality",
      JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    return EvaluationModality.valueOf(options[choice]);
  }

  @Override
  public List<StudentGrade> handleInserStudentGradeForModality(ModuleElement selectedModuleElement, List<Student> subscribedStudents, EvaluationModality evaluationModality) {
    List<StudentGrade> studentGrades = new ArrayList<>();
    for (Student student : subscribedStudents) {
      String gradeStr = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter grade for " + student.getFirstName() + " " + student.getLastName() + ":");
      double grade = Double.parseDouble(gradeStr);
      studentGrades.add(new StudentGradeBuilder()
        .studentGradeId(new StudentGradeId(student.getId(), selectedModuleElement.getCode(), evaluationModality))
        .grade(grade)
        .build());
    }
    return studentGrades;
  }

  @Override
  public void renderMenu(List<String> options) {
    StringBuilder sb = new StringBuilder("Menu:\n");
    for (String option : options) {
      sb.append(option).append("\n");
    }
    textArea.setText(sb.toString());
  }

  @Override
  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(MainFrame.getInstance(), message);
  }
}