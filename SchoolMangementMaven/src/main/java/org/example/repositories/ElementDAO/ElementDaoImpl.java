package org.example.repositories.ElementDAO;

import org.example.models.academique.Module;
import org.example.models.academique.ModuleElement;
import org.example.repositories.SuperRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElementDaoImpl extends SuperRepo implements ElementDao {

    private static ElementDaoImpl instance;

    public ElementDaoImpl() {
        super(myDatabase);
    }


    public static ElementDaoImpl getInstance() {
        if (instance == null) {
            instance = new ElementDaoImpl();
        }

        return instance;
    }

    public void add(ModuleElement element) throws SQLException {
        String elementcode = element.getCode();
        String query = "INSERT INTO ModuleElement (code, coefficient, isValidated, moduleCode) VALUES ('" + elementcode + "', " + element.getCoefficient() + ", " + element.isValidated() + ", '" + element.getParentModule().getCode() + "')";
        myDatabase.connect();
        myDatabase.executeQuery(query);
        myDatabase.disconnect();
    }

    public List<ModuleElement> findAll() throws SQLException {
        String query = "SELECT * FROM ModuleElement";
        myDatabase.connect();
        ResultSet resultSet = myDatabase.fetchResults(query);
        List<ModuleElement> elements = new ArrayList();
        while(resultSet.next()) {
            elements.add(new ModuleElement(
                    resultSet.getString("code"),
                    resultSet.getDouble("coefficient"),
                    resultSet.getBoolean("isValidated"),
                    new Module(resultSet.getString("moduleCode"))));
        }
        myDatabase.disconnect();
        return elements;

    }

    public Optional<ModuleElement> findByCode(String code) throws SQLException {
        String query = "SELECT * FROM ModuleElement WHERE code = '" + code + "'";
        myDatabase.connect();
        ResultSet resultSet = myDatabase.fetchResults(query);
        Optional<ModuleElement> element = Optional.empty();

        if (resultSet.next()) {
            Module module = new Module(resultSet.getString("moduleCode"));
            ModuleElement moduleElement = new ModuleElement(
                    resultSet.getString("code"),
                    resultSet.getDouble("coefficient"),
                    resultSet.getBoolean("isValidated"),
                    module
            );
            return Optional.of(moduleElement);
        }

        myDatabase.disconnect();
        return element;
    }

    public void update(ModuleElement element) throws SQLException {
        double elementCoefficient = element.getCoefficient();
        String query = "UPDATE ModuleElement SET coefficient = " + elementCoefficient + ", isValidated = " + element.isValidated() + ", moduleCode = '" + element.getParentModule().getCode()+ "' WHERE code = '" + element.getCode() + "'";
        myDatabase.connect();
        myDatabase.executeQuery(query);
        myDatabase.disconnect();
    }

    public void delete(String code) throws SQLException {
        String query = "DELETE FROM ModuleElement WHERE code = '" + code + "'";
        myDatabase.connect();
        myDatabase.executeQuery(query);
        myDatabase.disconnect() ;
    }
}
