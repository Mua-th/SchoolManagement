package StudentGradeTest;

import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.repositories.note.StudentGradeRepo;
import org.example.services.note.StudentGradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeServiceTest {

  @Mock
  private StudentGradeRepo studentGradeRepo;

  private StudentGradeService studentGradeService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    studentGradeService = StudentGradeService.getInstance();
  }

  @Test
  public void testCalculateWeightedAverage() {
    List<StudentGrade> studentGrades = Arrays.asList(
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), 10.0),
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.TP), 15.0),
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.PROJECT), 20.0)
    );

    double average = studentGradeService.calculateWeightedAverage(studentGrades);

    assertEquals(15.5, average);
  }


  @Test
  public void testSaveForAllModalities() throws SQLException {
    when(studentGradeRepo.save(any(StudentGrade.class))).thenReturn(true);

    boolean result = studentGradeService.saveForAllModalities("student1", "module1", 10.0, 15.0, 20.0);

    assertTrue(result);
  }

}