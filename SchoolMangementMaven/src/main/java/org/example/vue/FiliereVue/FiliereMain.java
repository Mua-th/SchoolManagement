package org.example.vue.FiliereVue;

import org.example.models.academique.Filiere;
import org.example.services.user.FiliereService.FiliereService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

/*
public class FiliereMain {
    public static void main(String[] args) {
        // Crée une instance de FiliereService
        FiliereService filiereService = new FiliereService();


        // Test 1: Ajouter une nouvelle filière
        Filiere newFiliere = new Filiere("INF002", "Informatique avancée");
        try {
            filiereService.addFiliere(newFiliere, "admin-1"); // Assurez-vous que "adminUser" a les droits d'administration
            System.out.println("Filière ajoutée avec succès!");
        } catch (SecurityException e) {
            System.out.println("Erreur: " + e.getMessage());
        }


        // Test 2: Mettre à jour une filière
        Filiere updatedFiliere = new Filiere("INF002", "Informatique avancée et IA");
        try {
            filiereService.updateFiliere(updatedFiliere, "admin-1"); // Assurez-vous que "adminUser" a les droits d'administration
            System.out.println("Filière mise à jour avec succès!");
        } catch (SecurityException e) {
            System.out.println("Erreur: " + e.getMessage());
        }


        // Test 3: Obtenir toutes les filières
        List<Filiere> filieres = filiereService.getAllFilieres();
        System.out.println("Liste des filières : ");
        for (Filiere filiere : filieres) {
            System.out.println(filiere.getCode() + " - " + filiere.getName());
        }

        // Test 4: Récupérer une filière par son code
        Optional<Filiere> filiereOptional = filiereService.getFiliereByCode("INF002");
        if (filiereOptional.isPresent()) {
            Filiere filiere = filiereOptional.get();
            System.out.println("Filière trouvée : " + filiere.getCode() + " - " + filiere.getName());
        } else {
            System.out.println("Filière non trouvée.");
        }

        // Test 5: Supprimer une filière
        try {
            filiereService.deleteFiliere("INF002", "admin-1"); // Assurez-vous que "adminUser" a les droits d'administration
            System.out.println("Filière supprimée avec succès!");
        } catch (SecurityException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}*/

public class FiliereMain {
    public static void main(String[] args) throws SQLException {
        // Récupère l'instance unique de FiliereService
        FiliereService filiereService = FiliereService.getInstance();

        // Test 1: Ajouter une nouvelle filière
        Filiere newFiliere = new Filiere("INF002", "Informatique avancée");
        try {
            filiereService.addFiliere(newFiliere); // Assurez-vous que "adminUser" a les droits d'administration
            System.out.println("Filière ajoutée avec succès!");
        } catch (SecurityException e) {
            System.out.println("Erreur: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Test 2: Mettre à jour une filière
        Filiere updatedFiliere = new Filiere("INF002", "Informatique avancée et IA");
        try {
            filiereService.updateFiliere(updatedFiliere);
            System.out.println("Filière mise à jour avec succès!");
        } catch (SecurityException | SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        // Test 3: Obtenir toutes les filières
        List<Filiere> filieres = filiereService.getAllFilieres();
        System.out.println("Liste des filières : ");
        for (Filiere filiere : filieres) {
            System.out.println(filiere.getCode() + " - " + filiere.getName());
        }

        // Test 4: Récupérer une filière par son code
        Optional<Filiere> filiereOptional = filiereService.getFiliereByCode("INF002");
        if (filiereOptional.isPresent()) {
            Filiere filiere = filiereOptional.get();
            System.out.println("Filière trouvée : " + filiere.getCode() + " - " + filiere.getName());
        } else {
            System.out.println("Filière non trouvée.");
        }

        // Test 5: Supprimer une filière
        try {
            filiereService.deleteFiliere("INF002");
            System.out.println("Filière supprimée avec succès!");
        } catch (SecurityException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}

