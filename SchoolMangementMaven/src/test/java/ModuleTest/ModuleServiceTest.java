package ModuleTest;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;
import org.example.repositories.academique.ModuleDao;
import org.example.services.academique.ModuleService;
import org.example.services.academique.ModuleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ModuleServiceTest {

  @Mock
  private ModuleDao moduleDaoMock;

  private ModuleService moduleService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);  // Initialize mocks
    moduleService = ModuleServiceImpl.getInstance();
    moduleDaoMock = mock(ModuleDao.class);
    moduleService.setModuleDao(moduleDaoMock);  // Inject the mock into the service
  }

  @Test
  public void testGetModuleByCode() throws SQLException {
    // Prepare the mock
    String code = "MOD101";
    Module module = new Module(code, "Module 101", Semester.valueOf("S1"), new Filiere("Informatique"));
    when(moduleDaoMock.findByCode(code)).thenReturn(Optional.of(module));

    // Call the service method
    Optional<Module> result = moduleService.getModuleByCode(code);

    // Verify the result
    assertTrue(result.isPresent());
    assertEquals("MOD101", result.get().getCode());
    verify(moduleDaoMock, times(1)).findByCode(code);
  }

  @Test
  public void testGetAllModules() throws SQLException {
    // Prepare the mock
    Module module1 = new Module("MOD101", "Module 101", Semester.valueOf("S1"), new Filiere("Informatique"));
    Module module2 = new Module("MOD102", "Module 102", Semester.valueOf("S1"), new Filiere("Mathematics"));
    List<Module> mockModules = Arrays.asList(module1, module2);
    when(moduleDaoMock.findAll()).thenReturn(mockModules);

    // Call the service method
    List<Module> result = moduleService.getAllModules();

    // Verify the result
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("MOD101", result.get(0).getCode());
    verify(moduleDaoMock, times(1)).findAll();
  }

  @Test
  public void testAddModule() throws SQLException {
    // Prepare the mock
    Module module = new Module("MOD103", "Module 103", Semester.valueOf("S3"), new Filiere("Informatique"));
    doNothing().when(moduleDaoMock).save(module);

    // Call the service method
    moduleService.addModule(module);

    // Verify the mock behavior
    verify(moduleDaoMock, times(1)).save(module);
  }

  @Test
  public void testAddModuleWithNullFiliere() {
    // Prepare the module with a null Filiere
    Module module = new Module("MOD104", "Module 104", Semester.valueOf("S4"), null);

    // Verify the expected exception
    assertThrows(IllegalArgumentException.class, () -> moduleService.addModule(module));
  }

  @Test
  public void testUpdateModule() throws SQLException {
    // Prepare the mock
    Module module = new Module("MOD105", "Module 105", Semester.valueOf("S2"), new Filiere("Informatique"));
    doNothing().when(moduleDaoMock).update(module);

    // Call the service method
    moduleService.updateModule(module);

    // Verify the mock behavior
    verify(moduleDaoMock, times(1)).update(module);
  }

  @Test
  public void testDeleteModule() throws SQLException {
    // Prepare the mock
    String code = "MOD106";
    doNothing().when(moduleDaoMock).delete(code);

    // Call the service method
    moduleService.deleteModule(code);

    // Verify the mock behavior
    verify(moduleDaoMock, times(1)).delete(code);
  }
}