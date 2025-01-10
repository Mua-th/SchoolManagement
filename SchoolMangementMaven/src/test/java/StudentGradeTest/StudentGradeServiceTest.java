package StudentGradeTest;

import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.repositories.academique.ModuleElementDao;
import org.example.repositories.note.StudentGradeRepo;
import org.example.services.note.AccessControl;
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

  @Mock
  private AccessControl accessControl;
  @Mock
  private ModuleElementDao moduleElementDao;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    studentGradeService = StudentGradeService.getInstance(studentGradeRepo, moduleElementDao, accessControl);
  }

  @Test
  public void testCalculateWeightedAverage() {
    List<StudentGrade> studentGrades = Arrays.asList(
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), 10.0),
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.TP), 10.0),
      new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.PROJECT), 20.0)
    );

    double average = studentGradeService.calculateWeightedAverage(studentGrades);

    assertEquals(15, average);
  }

  @Test
  public void testSaveForAllModalities() throws SQLException {
    when(studentGradeRepo.save(any(StudentGrade.class))).thenReturn(true);
    boolean result = studentGradeService.saveForAllModalities("student1", "module1", 10.0, 15.0, 20.0);
    assertTrue(result);
  }

  @Test
  public void testExportGradesToExcel_ProfNotResponsible() throws Exception {
    String moduleElementCode = "module1";
    String filePath = "grades.xlsx";
    // Mock the access control to return false
    when(accessControl.hasAccess(anyString(), eq(moduleElementCode))).thenReturn(false);
    Exception exception = assertThrows(Exception.class, () -> {
      studentGradeService.exportGradesToExcel(moduleElementCode, filePath);
    });
    assertEquals("User does not have access to this operation.", exception.getMessage());
  }
}