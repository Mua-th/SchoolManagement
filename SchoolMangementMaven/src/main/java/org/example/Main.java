package org.example;
import org.example.vue.FiliereVue.FiliereVue;
import org.example.repositories.UserRepository;
import org.example.vue.MenuPrinter;
import org.example.vue.PrintableToConsole;
import org.example.vue.StudentVue.StudentVue;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {

    // Obtenir l'instance unique de FiliereVue (Singleton)
    FiliereVue filiereVue = FiliereVue.getInstance();

    // Login : user : role

    // Afficher le menu pour démarrer les opérations
    filiereVue.afficherMenu();
    /*
    int choix = filiereVue.getChoix();

    switch ( choix ) {
      case AJOUTER_NOUVELLE:
        filiereVue.afficherSousMenu1();
        int sousCHois = filiereVue.getChoix();

        ;break;
    }
    //System.out.println(UserRepository.getInstance().findById("prof-1")) ;

    // gérer les students
    StudentVue studentVue = StudentVue.getInstance();
    studentVue.afficherMenu();
     */
  }


}