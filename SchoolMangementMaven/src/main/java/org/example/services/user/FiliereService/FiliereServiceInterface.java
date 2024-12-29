package org.example.services.user.FiliereService;
import org.example.models.academique.Module;
import org.example.models.academique.Filiere;
import java.util.List;
import java.util.Optional;

public interface FiliereServiceInterface {

    void addFiliere(Filiere filiere);
    void deleteFiliere(String code);
    void updateFiliere(Filiere filiere);
    List<Filiere> getAllFilieres();
    Optional<Filiere> getFiliereByCode(String code);
    List<Module> getModulesByFiliereCode(String code);
}
