package org.example.controllers;

import org.example.models.academique.Filiere;
import org.example.services.user.FiliereService.FiliereService;
import org.example.models.academique.Module;
import org.example.zapp.vue.Admin.AdminView;
import org.example.zapp.vue.Admin.AdminViewInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminController {
//  private final AppState state;
//  private final Renderer renderer;

  private AdminViewInterface adminView ;


  public AdminController(AdminViewInterface adminView) {
     this.adminView = AdminView.getInstance();
  }

  public void handleInput() throws SQLException {

    String choice = adminView.getUserChoice();

    switch (choice) {
      case "1":
        handleGestionFilières();
        break;
      case "2":
        handleManageStudents();
        break;
      case "3":
        handleManagePrograms();
        break;
      case "4":
//        state.setAuthenticated(false);
//        state.setUsername(null);
//        state.setCurrentMenu("login");
//        System.out.println("\nYou have logged out successfully.");
        break;
      default:
        System.out.println("\nInvalid choice. Please try again.");
    }

   // renderer.render();
  }

  private void handleGestionFilières() throws SQLException {
    AdminView.getInstance().displayGestionFiliereMenu();
    String choix = adminView.getUserChoice();
    handleMenuFiliere(choix);
  }

  public void handleMenuFiliere(String  choix) throws SQLException {
    switch (choix) {
      case "1":
        ajouterFiliere();
        break;
      case "2":
        mettreAJourFiliere();
        break;
      case "3":
        supprimerFiliere();
        break;
      case "4":
        afficherFilieres();
        break;
      case "5":
        rechercherFiliere();
        break;
      case "6":
        afficherModulesParFiliere();
        break;
      case "7":
        System.out.println("Au revoir !");
        break;
      default:
        System.out.println("Choix invalide, veuillez réessayer.");
        break;
    }
  }

  private void afficherModulesParFiliere() throws SQLException {
    String code = adminView.getFiliereCode();
    List<Module> modules = FiliereService.getInstance().getModulesByFiliereCode(code);
    adminView.afficherModulesParFiliere(modules);
  }

  private void afficherFilieres() throws SQLException {
    List<Filiere> filieres = FiliereService.getInstance().getAllFilieres();
   adminView.afficherFilieres(filieres);
  }

  private void rechercherFiliere() throws SQLException {
   String code = adminView.getFiliereCode();
   Optional<Filiere> filiere = FiliereService.getInstance().getFiliereByCode(code);
   if(filiere.isPresent()){
     adminView.afficherFiliere(filiere.get());
   }
  }

  private void supprimerFiliere() throws SQLException {
    String code = adminView.supprimerFiliere();
    FiliereService.getInstance().deleteFiliere(code);
  }

  private void mettreAJourFiliere() throws SQLException {
    Filiere filiere = adminView.mettreAJourFiliere();
    FiliereService.getInstance().updateFiliere(filiere);
  }

  private void ajouterFiliere() throws SQLException {
    Filiere filiere = adminView.ajouterFiliere();
    FiliereService.getInstance().addFiliere(filiere);
  }

  private void handleManageProfessors() {
    // Implement logic to manage professors
    System.out.println("Managing professors...");
    // Example: List all professors, add a new professor, etc.
  }

  private void handleManageStudents() {
    // Implement logic to manage students
    System.out.println("Managing students...");
    // Example: List all students, add a new student, etc.
  }

  private void handleManagePrograms() {
    // Implement logic to manage programs
    System.out.println("Managing programs...");
    // Example: List all programs, add a new program, etc.
  }


}