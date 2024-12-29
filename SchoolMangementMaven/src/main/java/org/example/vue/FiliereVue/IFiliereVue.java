package org.example.vue.FiliereVue;

import java.sql.SQLException;

public interface IFiliereVue {

    public void afficherMenu();

    public void ajouterFiliere();

    public void mettreAJourFiliere() throws SQLException;

    public void supprimerFiliere() throws SQLException;

    public void afficherFilieres() throws SQLException;

    public void rechercherFiliere() throws SQLException;


}
