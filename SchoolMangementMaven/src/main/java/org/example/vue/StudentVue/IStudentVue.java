package org.example.vue.StudentVue;

import java.sql.SQLException;

public interface IStudentVue {

    // Méthode pour afficher le menu principal
    void afficherMenu();

    // Méthode pour ajouter un étudiant
    void ajouterStudent() throws SQLException;

    // Méthode pour mettre à jour un étudiant
    void mettreAJourStudent() throws SQLException;

    // Méthode pour supprimer un étudiant
    void supprimerStudent() throws SQLException;

    // Méthode pour afficher tous les étudiants
    void afficherStudents() throws SQLException;

    // Méthode pour rechercher un étudiant par nom
    void rechercherStudent() throws SQLException;
}
