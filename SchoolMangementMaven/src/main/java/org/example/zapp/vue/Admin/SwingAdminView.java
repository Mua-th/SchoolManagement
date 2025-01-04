package org.example.zapp.vue.Admin;

import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.users.Student.Student;

import java.util.List;

public class SwingAdminView implements  AdminViewInterface{
    @Override
    public void displayAdminMenu() {

    }

    @Override
    public String getUserChoice() {
        return "";
    }

    @Override
    public void displayGestionFiliereMenu() {

    }

    @Override
    public Filiere ajouterFiliere() {
        return null;
    }

    @Override
    public Filiere mettreAJourFiliere() {
        return null;
    }

    @Override
    public String supprimerFiliere() {
        return "";
    }

    @Override
    public void afficherFilieres(List<Filiere> filieres) {

    }

    @Override
    public String getFiliereCode() {
        return "";
    }

    @Override
    public String getStudentName() {
        return "";
    }

    @Override
    public void afficherFiliere(Filiere filiere) {

    }

    @Override
    public void afficherModulesParFiliere(List<Module> modules) {

    }

    @Override
    public void displayGestionetudiantMenu() {

    }

    @Override
    public Student GetStudent() {
        return null;
    }

    @Override
    public void afficherStudent(Student student) {

    }

    @Override
    public Student mettreAJourStudent() {
        return null;
    }

    @Override
    public String supprimerStudent() {
        return "";
    }

    @Override
    public void afficherStudents(List<Student> students) {

    }

    @Override
    public void rechercherStudent(Student s) {

    }

    @Override
    public void afficherMessageajoutetudiant(boolean b) {

    }
}
