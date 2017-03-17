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

    private Connection con = null;

    
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

    // AÑADE UNA TALLA EN MYSQL
    public void add(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO TALLA (nombre)VALUES('"+tall.getNombre() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error adding talla");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error adding talla in DAO", se);
        }
    }

    // ACTUALIZA UNA TALLA EN MYSQL
    public void update(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE TALLA "
                    + "SET NOMBRE='" + tall.getNombre() + "' "
                  
                    + "WHERE IDTALLA='" + tall.getIdTalla()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando talla");
            }
        } catch (SQLException se) {
            throw new DAOException("Error updating talla in DAO", se);
        }
    }

    // BORRA UNA TALLA EN MYSQL
    public void deleteIdTalla(int idtalla) throws DAOException {
        Talla tall = findByIdTalla(idtalla);
        if (tall == null) {
            throw new DAOException("Talla id: " + idtalla + " does not exist to delete.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM TALLA WHERE IDTALLA=" + idtalla;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error deleting talla");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error deleting talla in DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Talla findByIdTalla(int id) throws DAOException {
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

    
    public Talla[] getAllTablasTalla() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TALLA";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Talla> tallas = new ArrayList<>();
            
            while (rs.next()) {
                tallas.add(new Talla(rs.getInt("IDTALLA"), rs.getString("NOMBRE")));
            }
            return tallas.toArray(new Talla[0]);
        } catch (SQLException se) {
            
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