package org.example.zapp.vue.Admin;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;

import java.util.List;

public interface AdminViewInterface {
    void displayAdminMenu();

    String getUserChoice();

    void displayGestionFiliereMenu();

    // Ajouter une filière
    Filiere ajouterFiliere();

    // Mettre à jour une filière
    Filiere mettreAJourFiliere();

    // Supprimer une filière
    String supprimerFiliere();

    // Afficher toutes les filières
    void afficherFilieres(List<Filiere> filieres);

    // Rechercher une filière par code
    String  getFiliereCode();

    void afficherFiliere(Filiere filiere);

    void afficherModulesParFiliere(List<Module> modules);
}
