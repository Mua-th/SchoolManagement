package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.example.config.MySQLDatabase;
import org.example.models.academique.Filiere;
import org.example.models.academique.Module;
import org.example.models.academique.Semester;
public class ModuleDaoImplhouda {

    private final MySQLDatabase database = MySQLDatabase.getInstance();
    private static ModuleDaoImplhouda instance;

    private ModuleDaoImplhouda() {
    }

    public static ModuleDaoImpl getInstance() {
        if (instance == null) {
            instance = new ModuleDaoImplhouda();
        }

        return instance;
    }

    public Module findByCode(String code) {
        String query = "SELECT * FROM module WHERE code = ?";

        try {
            Connection conn = this.database.connect();

            Module var6;
            label114: {
                try {
                    PreparedStatement stmt;
                    label106: {
                        stmt = conn.prepareStatement(query);

                        try {
                            stmt.setString(1, code);
                            ResultSet rs = stmt.executeQuery();

                            label89: {
                                try {
                                    if (rs.next()) {
                                        var6 = this.mapToModule(rs);
                                        break label89;
                                    }
                                } catch (Throwable var11) {
                                    if (rs != null) {
                                        try {
                                            rs.close();
                                        } catch (Throwable var10) {
                                            var11.addSuppressed(var10);
                                        }
                                    }

                                    throw var11;
                                }

                                if (rs != null) {
                                    rs.close();
                                }
                                break label106;
                            }

                            if (rs != null) {
                                rs.close();
                            }
                        } catch (Throwable var12) {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (Throwable var9) {
                                    var12.addSuppressed(var9);
                                }
                            }

                            throw var12;
                        }

                        if (stmt != null) {
                            stmt.close();
                        }
                        break label114;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Throwable var13) {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (Throwable var8) {
                            var13.addSuppressed(var8);
                        }
                    }

                    throw var13;
                }

                if (conn != null) {
                    conn.close();
                }

                return null;
            }

            if (conn != null) {
                conn.close();
            }

            return var6;
        } catch (SQLException var14) {
            SQLException e = var14;
            e.printStackTrace();
            return null;
        }
    }

    public List<Module> findAll() {
        List<Module> modules = new ArrayList();
        String query = "SELECT * FROM module m JOIN filiere f ON m.filiereCode = f.code";

        try {
            Connection connection = this.database.connect();

            try {
                PreparedStatement stmt = connection.prepareStatement(query);

                try {
                    ResultSet rs = stmt.executeQuery();

                    try {
                        while(rs.next()) {
                            Module module = new Module();
                            module.setCode(rs.getString("code"));
                            module.setName(rs.getString("name"));
                            module.setSemester(Semester.valueOf(rs.getString("semester")));
                            Filiere filiere = new Filiere();
                            filiere.setCode(rs.getString("code"));
                            filiere.setName(rs.getString("name"));
                            module.setFiliere(filiere);
                            modules.add(module);
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            e.printStackTrace();
        }

        return modules;
    }

    public void save(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null");
        } else if (module.getFiliere() == null) {
            throw new IllegalArgumentException("Filiere cannot be null");
        } else {
            try {
                this.ensureFiliereExists(module);
            } catch (SQLException var9) {
                SQLException e = var9;
                e.printStackTrace();
                throw new RuntimeException("Failed to validate Filiere before saving Module.");
            }

            String query = "INSERT INTO module (code, name, semester, filiereCode) VALUES (?, ?, ?, ?)";

            try {
                Connection conn = this.database.connect();

                try {
                    PreparedStatement stmt = conn.prepareStatement(query);

                    try {
                        if (conn == null) {
                            throw new SQLException("Database connection is null.");
                        }

                        stmt.setString(1, module.getCode());
                        stmt.setString(2, module.getName());
                        stmt.setString(3, module.getSemester().getDatabaseValue());
                        stmt.setString(4, module.getFiliere().getCode());
                        stmt.executeUpdate();
                    } catch (Throwable var10) {
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var8) {
                                var10.addSuppressed(var8);
                            }
                        }

                        throw var10;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Throwable var11) {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (Throwable var7) {
                            var11.addSuppressed(var7);
                        }
                    }

                    throw var11;
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var12) {
                SQLException e = var12;
                e.printStackTrace();
            }

        }
    }

    public void update(Module module) {
        String query = "UPDATE module SET name = ?, semester = ?, filiereCode = ? WHERE code = ?";

        try {
            Connection conn = this.database.connect();

            try {
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    stmt.setString(1, module.getName());
                    stmt.setString(2, module.getSemester().name());
                    stmt.setString(3, module.getFiliere().getCode());
                    stmt.setString(4, module.getCode());
                    stmt.executeUpdate();
                } catch (Throwable var9) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

    }

    public void delete(String code) {
        String query = "DELETE FROM module WHERE code = ?";

        try {
            Connection conn = this.database.connect();

            try {
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    stmt.setString(1, code);
                    stmt.executeUpdate();
                } catch (Throwable var9) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var10) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            e.printStackTrace();
        }

    }

    private Module mapToModule(ResultSet rs) throws SQLException {
        String code = rs.getString("code");
        String name = rs.getString("name");
        String semesterStr = rs.getString("semester");
        String filiereCode = rs.getString("filiereCode");
        Filiere filiere = this.findFiliereByCode(filiereCode);
        if (filiere == null) {
            throw new SQLException("Filiere with code " + filiereCode + " not found");
        } else {
            return new Module(code, name, Semester.valueOf(semesterStr), filiere);
        }
    }

    private Filiere findFiliereByCode(String filiereCode) throws SQLException {
        String query = "SELECT * FROM filiere WHERE code = ?";
        Connection conn = this.database.connect();

        Filiere var7;
        label95: {
            try {
                PreparedStatement stmt;
                label97: {
                    stmt = conn.prepareStatement(query);

                    try {
                        stmt.setString(1, filiereCode);
                        ResultSet rs = stmt.executeQuery();

                        label83: {
                            try {
                                if (rs.next()) {
                                    String filiereName = rs.getString("name");
                                    var7 = new Filiere(filiereCode, filiereName);
                                    break label83;
                                }
                            } catch (Throwable var11) {
                                if (rs != null) {
                                    try {
                                        rs.close();
                                    } catch (Throwable var10) {
                                        var11.addSuppressed(var10);
                                    }
                                }

                                throw var11;
                            }

                            if (rs != null) {
                                rs.close();
                            }
                            break label97;
                        }

                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Throwable var12) {
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var9) {
                                var12.addSuppressed(var9);
                            }
                        }

                        throw var12;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                    break label95;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }

            return null;
        }

        if (conn != null) {
            conn.close();
        }

        return var7;
    }

    private void ensureFiliereExists(Module module) throws SQLException {
        if (module.getFiliere() != null && module.getFiliere().getCode() != null) {
            String query = "SELECT COUNT(*) FROM filiere WHERE code = ?";
            Connection conn = this.database.connect();

            try {
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    stmt.setString(1, module.getFiliere().getCode());
                    ResultSet rs = stmt.executeQuery();

                    try {
                        if (rs.next() && rs.getInt(1) == 0) {
                            throw new SQLException("Filiere with code '" + module.getFiliere().getCode() + "' does not exist.");
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }

        } else {
            throw new IllegalArgumentException("Filiere is null or has no code.");
        }
    }

    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList();
        String query = "SELECT DISTINCT semester FROM module";

        try {
            Connection conn = this.database.connect();

            try {
                Statement stmt = conn.createStatement();

                try {
                    ResultSet rs = stmt.executeQuery(query);

                    try {
                        while(rs.next()) {
                            semesters.add(Semester.valueOf(rs.getString("semester").toUpperCase()));
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            e.printStackTrace();
        }

        return semesters;
    }

    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = new ArrayList();

        try {
            Connection connection = this.database.connect();

            try {
                Statement statement = connection.createStatement();

                try {
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM filiere");

                    try {
                        while(resultSet.next()) {
                            String code = resultSet.getString("code");
                            String name = resultSet.getString("name");
                            filieres.add(new Filiere(code, name));
                        }
                    } catch (Throwable var10) {
                        if (resultSet != null) {
                            try {
                                resultSet.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (Throwable var11) {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (Throwable var12) {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Throwable var7) {
                        var12.addSuppressed(var7);
                    }
                }

                throw var12;
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var13) {
            SQLException e = var13;
            e.printStackTrace();
        }

        return filieres;
    }
}
