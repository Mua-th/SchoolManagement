package org.example.vue.ElementVue;

import java.sql.SQLException;

public interface ElementView {
    void displayAllElements() throws SQLException;

    void displayElementDetails(String var1) throws SQLException;

    void addElement() throws SQLException;

    void updateElement() throws SQLException;

    void deleteElement() throws SQLException;
}
