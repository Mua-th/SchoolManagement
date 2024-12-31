package org.example.dao;

import org.example.config.Database;
import org.example.config.MySQLDatabase;
import org.example.models.academique.Module;
import org.example.models.academique.ModuleElement;
import org.example.repositories.SuperRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String var10000 = element.getCode();
        String query = "INSERT INTO ModuleElement (code, coefficient, isValidated, moduleCode) VALUES ('" + var10000 + "', " + element.getCoefficient() + ", " + element.isValidated() + ", '" + element.getParentModule() + "')";
        myDatabase.connect();
        myDatabase.executeQuery(query);
        myDatabase.disconnect();
    }

    public List<ModuleElement> findAll() throws SQLException {
        String query = "SELECT * FROM ModuleElement";
        myDatabase.connect();
        ResultSet resultSet = myDatabase.fetchResults(query);
        myDatabase.disconnect();
        List<ModuleElement> elements = new ArrayList();

        while(resultSet.next()) {
            elements.add(new ModuleElement(
              resultSet.getString("code"),
              resultSet.getDouble("coefficient"),
              resultSet.getBoolean("isValidated"),
              new Module(resultSet.getString("moduleCode"))));
        }

        return elements;
    }

    public ModuleElement findByCode(String code) throws SQLException {
        String query = "SELECT * FROM ModuleElement WHERE code = '" + code + "'";
        myDatabase.connect();
        ResultSet resultSet = myDatabase.fetchResults(query);
        myDatabase.disconnect();
        return resultSet.next() ? new ModuleElement(resultSet.getString("code"),
          resultSet.getDouble("coefficient"),
          resultSet.getBoolean("isValidated"),
          new Module(resultSet.getString("moduleCode"))) : null;
    }

    public void update(ModuleElement element) throws SQLException {
        double var10000 = element.getCoefficient();
        String query = "UPDATE ModuleElement SET coefficient = " + var10000 + ", isValidated = " + element.isValidated() + ", moduleCode = '" + element.getParentModule().getCode()+ "' WHERE code = '" + element.getCode() + "'";
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
