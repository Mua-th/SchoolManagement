package TestFiliere;
import org.example.models.academique.Module;
import org.example.models.academique.Filiere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestClasseFiliere {

    private Filiere filiere;

    @BeforeEach
    void setUp() {
        filiere = new Filiere("CS01", "Computer Science");
    }

    @Test
    void testConstructor() {
        assertEquals("CS01", filiere.getCode());
        assertEquals("Computer Science", filiere.getName());
        assertTrue(filiere.getModules().isEmpty());
    }

    @Test
    void testGetCode() {
        assertEquals("CS01", filiere.getCode());
    }

    @Test
    void testGetName() {
        assertEquals("Computer Science", filiere.getName());
    }

    @Test
    void testGetModules() {
        assertTrue(filiere.getModules().isEmpty());
    }

    @Test
    void testSetCode() {
        filiere.setCode("CS02");
        assertEquals("CS02", filiere.getCode());
    }

    @Test
    void testSetName() {
        filiere.setName("Data Science");
        assertEquals("Data Science", filiere.getName());
    }

    @Test
    void testSetModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(new Module("M1", "Mathematics"));
        modules.add(new Module("M2", "Programming"));

        filiere.setModules(modules);
        assertEquals(2, filiere.getModules().size());
        assertEquals("M1", filiere.getModules().get(0).getCode());
        assertEquals("Mathematics", filiere.getModules().get(0).getName());
    }

    @Test
    void testToString() {
        String expected = "Filiere{code='CS01', name='Computer Science', modules=[]}";
        assertEquals(expected, filiere.toString());
    }
}


