package org.example.dao;

import org.example.config.MySQLDatabase;
import org.example.models.academique.ModuleElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElementDaoImpl implements ElementDao {
    private final MySQLDatabase database = MySQLDatabase.getInstance();
    private static ElementDaoImpl instance;

    private ElementDaoImpl() {
    }

    public static ElementDaoImpl getInstance() {
        if (instance == null) {
            instance = new ElementDaoImpl();
        }

        return instance;
    }

    public void add(ModuleElement element) throws SQLException {
        String var10000 = element.getCode();
        String query = "INSERT INTO ModuleElement (code, coefficient, isValidated, moduleCode) VALUES ('" + var10000 + "', " + element.getCoefficient() + ", " + element.isValidated() + ", '" + element.getModuleCode() + "')";
        this.database.connect();
        this.database.executeQuery(query);
    }

    public List<ModuleElement> findAll() throws SQLException {
        String query = "SELECT * FROM ModuleElement";
        this.database.connect();
        ResultSet resultSet = this.database.fetchResults(query);
        List<ModuleElement> elements = new ArrayList();

        while(resultSet.next()) {
            elements.add(new ModuleElement(resultSet.getString("code"), resultSet.getDouble("coefficient"), resultSet.getBoolean("isValidated"), resultSet.getString("moduleCode")));
        }

        return elements;
    }

    public ModuleElement findByCode(String code) throws SQLException {
        String query = "SELECT * FROM ModuleElement WHERE code = '" + code + "'";
        this.database.connect();
        ResultSet resultSet = this.database.fetchResults(query);
        return resultSet.next() ? new ModuleElement(resultSet.getString("code"), resultSet.getDouble("coefficient"), resultSet.getBoolean("isValidated"), resultSet.getString("moduleCode")) : null;
    }

    public void update(ModuleElement element) throws SQLException {
        double var10000 = element.getCoefficient();
        String query = "UPDATE ModuleElement SET coefficient = " + var10000 + ", isValidated = " + element.isValidated() + ", moduleCode = '" + element.getModuleCode() + "' WHERE code = '" + element.getCode() + "'";
        this.database.connect();
        this.database.executeQuery(query);
    }

    public void delete(String code) throws SQLException {
        String query = "DELETE FROM ModuleElement WHERE code = '" + code + "'";
        this.database.connect();
        this.database.executeQuery(query);
    }
}
