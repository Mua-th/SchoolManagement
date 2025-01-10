package StudentGradeTest;

import org.example.config.Database;
import org.example.models.note.EvaluationModality;
import org.example.models.note.StudentGrade;
import org.example.models.note.StudentGradeId;
import org.example.repositories.note.StudentGradeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeRepoTest {

  @Mock
  private Database myDatabase;

  @Mock
  private Connection connection;

  @Mock
  private PreparedStatement preparedStatement;

  @Mock
  private ResultSet resultSet;

  private StudentGradeRepo studentGradeRepo;

  @BeforeEach
  public void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    studentGradeRepo = StudentGradeRepo.getInstance();
    studentGradeRepo.setDatabase(myDatabase); // Set the mocked database
    when(myDatabase.connect()).thenReturn(connection);
  }

  @Test
  public void testFindById() throws SQLException {
    StudentGradeId studentGradeId = new StudentGradeId("student1", "module1", EvaluationModality.EXAM);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(resultSet.getDouble("grade")).thenReturn(15.0);
    StudentGrade studentGrade = studentGradeRepo.findById(studentGradeId);
    assertNotNull(studentGrade);
    assertEquals(15.0, studentGrade.getGrade());
  }

  @Test
  public void saveShouldInsertNewRecordWhenNotExists() throws SQLException {
    StudentGrade studentGrade = new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), 15.0);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(resultSet.getInt(1)).thenReturn(0);
    when(preparedStatement.executeUpdate()).thenReturn(1);

    boolean result = studentGradeRepo.save(studentGrade);

    assertTrue(result);
  }

  @Test
  public void saveShouldUpdateExistingRecord() throws SQLException {
    StudentGrade studentGrade = new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), 18.0);
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(resultSet.getInt(1)).thenReturn(1);
    when(preparedStatement.executeUpdate()).thenReturn(1);
    boolean result = studentGradeRepo.save(studentGrade);
    assertTrue(result);
  }

  @Test
  public void saveShouldThrowExceptionForNegativeGrade() {
    StudentGrade studentGrade = new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), -1.0);
    assertThrows(IllegalArgumentException.class, () -> {
      studentGradeRepo.save(studentGrade);
    });
  }

  @Test
  public void saveShouldThrowExceptionForGradeGreaterThanTwenty() {
    StudentGrade studentGrade = new StudentGrade(new StudentGradeId("student1", "module1", EvaluationModality.EXAM), 21.0);
    assertThrows(IllegalArgumentException.class, () -> {
      studentGradeRepo.save(studentGrade);
    });
  }
}