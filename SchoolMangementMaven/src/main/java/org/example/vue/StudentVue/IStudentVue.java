package org.example.vue.StudentVue;

public interface IStudentVue {

    // Méthode pour afficher le menu principal
    void afficherMenu();

    // Méthode pour ajouter un étudiant
    void ajouterStudent();

    // Méthode pour mettre à jour un étudiant
    void mettreAJourStudent();

    // Méthode pour supprimer un étudiant
    void supprimerStudent();

    // Méthode pour afficher tous les étudiants
    void afficherStudents();

    // Méthode pour rechercher un étudiant par nom
    void rechercherStudent();
}
