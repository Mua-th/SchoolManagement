package org.example.zapp.vue.Admin;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
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
