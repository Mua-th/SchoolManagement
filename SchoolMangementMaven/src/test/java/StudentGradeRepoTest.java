import org.example.config.Database;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.repositories.StudentGradeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeRepoTest {

  @Mock
  private Database dbConnection;

  @Mock
  private Connection connection;

  @Mock
  private ResultSet resultSet;

  @InjectMocks
  private StudentGradeRepo studentGradeRepo;

  @BeforeEach
  public void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    when(dbConnection.connect()).thenReturn(connection);
  }

  @Test
  public void testGetStudentGrades() throws SQLException {
    String studentId = "123";
    when(dbConnection.fetchResults(anyString())).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true, false);
    when(resultSet.getString("moduleElementCode")).thenReturn("MATH101");
    when(resultSet.getString("grade")).thenReturn("15.0");
    when(resultSet.getString("modality")).thenReturn("Exam");

    List<StudentGrade> studentGrades = studentGradeRepo.getStudentGrades(studentId);

    assertNotNull(studentGrades);
    assertEquals(1, studentGrades.size());
    StudentGrade studentGrade = studentGrades.get(0);
    assertEquals("123", studentGrade.getStudentGradeId().getStudentId());
    assertEquals("MATH101", studentGrade.getStudentGradeId().getModuleElementCode());
    assertEquals("Exam", studentGrade.getStudentGradeId().getEvaluationModality());
    assertEquals(15.0, studentGrade.getGrade());
  }

  @Test
  public void testSaveStudentGrade() throws SQLException {
    StudentGradeId studentGradeId = new StudentGradeId("123", "MATH101", "Exam");
    StudentGrade studentGrade = new StudentGrade(studentGradeId, 15.0);


    doNothing().when(dbConnection).executeQuery(anyString());

    boolean result = studentGradeRepo.save(studentGrade);

    assertTrue(result);
    verify(dbConnection, times(1)).executeQuery(anyString());
  }
}