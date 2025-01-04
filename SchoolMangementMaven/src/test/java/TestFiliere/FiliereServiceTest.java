package TestFiliere;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.repositories.FiliereDAO.FiliereDAO;

import org.example.services.user.FiliereService.FiliereService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiliereServiceTest {

    @InjectMocks
    private FiliereService filiereService; // Injecte la classe à tester

    @Mock
    private FiliereDAO filiereDAO; // Mock de la DAO

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    void testAddFiliere() throws SQLException {
        Filiere filiere = new Filiere("CS101", "Computer Science");
        doNothing().when(filiereDAO).addFiliere(filiere);

        filiereService.addFiliere(filiere);

        verify(filiereDAO, times(1)).addFiliere(filiere); // Vérifie l'appel
    }

    @Test
    void testDeleteFiliere() throws SQLException {
        String code = "CS101";
        doNothing().when(filiereDAO).deleteFiliere(code);

        filiereService.deleteFiliere(code);

        verify(filiereDAO, times(1)).deleteFiliere(code); // Vérifie l'appel
    }

    @Test
    void testUpdateFiliere() throws SQLException {
        Filiere filiere = new Filiere("CS101", "Computer Science Updated");
        doNothing().when(filiereDAO).updateFiliere(filiere);

        filiereService.updateFiliere(filiere);

        verify(filiereDAO, times(1)).updateFiliere(filiere);
    }

    @Test
    void testGetAllFilieres() throws SQLException {
        List<Filiere> filieres = Arrays.asList(
                new Filiere("CS101", "Computer Science"),
                new Filiere("IT102", "Information Technology")
        );
        when(filiereDAO.getAllFilieres()).thenReturn(filieres);

        List<Filiere> result = filiereService.getAllFilieres();

        assertEquals(2, result.size());
        assertEquals("CS101", result.get(0).getCode());
        verify(filiereDAO, times(1)).getAllFilieres();
    }

    @Test
    void testGetFiliereByCode() throws SQLException {
        Filiere filiere = new Filiere("CS101", "Computer Science");
        when(filiereDAO.getFiliereByCode("CS101")).thenReturn(Optional.of(filiere));

        Optional<Filiere> result = filiereService.getFiliereByCode("CS101");

        assertTrue(result.isPresent());
        assertEquals("CS101", result.get().getCode());
        verify(filiereDAO, times(1)).getFiliereByCode("CS101");
    }

    @Test
    void testGetModulesByFiliereCode() throws SQLException {
        Module module1 = new Module("M101", "Programming");
        Module module2 = new Module("M102", "Databases");
        List<Module> modules = Arrays.asList(module1, module2);

        when(filiereDAO.getModulesByFiliereCode("CS101")).thenReturn(modules);

        List<Module> result = filiereService.getModulesByFiliereCode("CS101");

        assertEquals(2, result.size());
        assertEquals("M101", result.get(0).getCode());
        verify(filiereDAO, times(1)).getModulesByFiliereCode("CS101");
    }
}

