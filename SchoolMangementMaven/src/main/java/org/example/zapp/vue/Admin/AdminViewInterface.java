package org.example.zapp.vue.Admin;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;

import org.example.models.academique.ModuleElement;

import org.example.models.users.Student.Student;


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

    String getStudentName();

    void afficherFiliere(Filiere filiere);

    void afficherModulesParFiliere(List<Module> modules);



// La partie des éléments

    void displayGestionElementMenu();

    // Ajouter une filière
    ModuleElement ajouterElement();

    // Mettre à jour une filière
    ModuleElement mettreAJourElement();

    // Supprimer une filière
    String supprimerElement();

    // Afficher toutes les filières
    void afficherElements(List<ModuleElement> Elements);

    // Rechercher une filière par code
    String  getElementbyCode();

    void afficherElement(ModuleElement element);

    // La partie des Modules

    void displayGestionModuleMenu();

    // Ajouter une filière
    Module ajouterModule();

    // Mettre à jour une filière
    Module mettreAJourModule();

    // Supprimer une filière
    String supprimerModule();

    // Afficher toutes les filières
    void afficherModules(List<Module> modules);

    // Rechercher une filière par code
    String  getModulebyCode();

    void afficherModule(Module module);




    //Partie students

    void displayGestionetudiantMenu();

    Student GetStudent();

    void afficherStudent(Student student);
    // Méthode pour mettre à jour un étudiant
    Student mettreAJourStudent();

    // Méthode pour supprimer un étudiant
    String supprimerStudent();

    // Méthode pour afficher tous les étudiants
    void afficherStudents(List<Student> students);

    // Méthode pour rechercher un étudiant par nom


    // Méthode pour rechercher un étudiant par nom
    void rechercherStudent(Student s);

    void afficherMessageajoutetudiant(boolean b);

}
