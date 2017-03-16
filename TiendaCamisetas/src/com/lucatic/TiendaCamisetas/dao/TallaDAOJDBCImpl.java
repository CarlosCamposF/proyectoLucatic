package com.lucatic.TiendaCamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.TiendaCamisetas.model.Talla;

public class TallaDAOJDBCImpl implements TallaDAO {

    // Not thread-safe
    private Connection con = null;

    // package level access
    TallaDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error obtaining connection with the database: " + se);
            Logger.getLogger(TallaDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TallaDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Add an Talla record using an INSERT SQL command
    public void add(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO TALLA VALUES(" + tall.getIdTalla() + ","
                    + "'" + tall.getNombre() + ")";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error adding talla");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error adding talla in DAO", se);
        }
    }

    // Update an talla record with a SQL UPDATE command
    public void update(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "UPDATE TALLA "
                    + "SET NOMBRE='" + tall.getNombre() + "',"
                  
                    + "WHERE IDTALLA=" + tall.getIdTalla();
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error updating talla");
            }
        } catch (SQLException se) {
            throw new DAOException("Error updating talla in DAO", se);
        }
    }

    // Delete an employee record that has this ID from the database using the DELETE command
    public void delete(int idtalla) throws DAOException {
        Talla tall = findById(idtalla);
        if (tall == null) {
            throw new DAOException("Talla id: " + idtalla + " does not exist to delete.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM TALLA WHERE IDTALLA=" + idtalla;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error deleting talla");
            }
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error deleting talla in DAO", se);
        }
    }

    // Find an Talla record using this ID
    public Talla findById(int id) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TALLA WHERE IDTALLA=" + id;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Talla(rs.getInt("IDTALLA"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding talla in DAO", se);
        }
    }

    // Return an array of all of the Employee records
    // We are using a collection List object to store the results
    // This makes it easier to just add to the collection
    public Talla[] getAllTallas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TALLA";
            ResultSet rs = stmt.executeQuery(query);
            // Create an ArrayList to save resulting records
            ArrayList<Talla> tallas = new ArrayList<>();
            // Iterate through the results and create Employee objects
            while (rs.next()) {
                tallas.add(new Talla(rs.getInt("IDTALLA"), rs.getString("NOMBRE")));
            }
            return tallas.toArray(new Talla[0]);
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error getting all tallas in DAO: " + se.getMessage(), se);
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println ("Exception closing Connection: " + se);
        }
    }
}