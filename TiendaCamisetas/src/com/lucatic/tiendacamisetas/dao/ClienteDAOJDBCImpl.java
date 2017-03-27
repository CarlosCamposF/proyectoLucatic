package com.lucatic.tiendacamisetas.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Cliente;
import com.lucatic.tiendacamisetas.beans.Usuario;
import com.lucatic.tiendacamisetas.model.Rol;

public class ClienteDAOJDBCImpl implements ClienteDAO {
	
	 private Connection con = null;
	
	 //CONEXIÓN CON LA BASE DE DATOS**********************************
     ClienteDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(TipoPagoDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TipoPagoDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//****************************************************************
    
    
    // AÑADE UN CLIENTE EN MYSQL
    @Override
	public void addItem(Cliente item) throws DAOException {
		try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO CLIENTE (NOMBRE,APELLIDO,DNI,DIRECCION,CORREO,TELEFONO1,TELEFONO2,USUARIO)"
            				+ " VALUES ('"+item.getNombre()+ "','"
            							+item.getApellidos()+"','"
            							+item.getDni() + "','"
            							+item.getDireccion()+"','"
            							+item.getCorreo()+"',"
            							+item.getTelefono1()+","
            							+item.getTelefono2()+","
            							+item.getUsuario().getIdUsuario()+")";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo cliente");
            }
            
        } catch (SQLException se) {
        	se.printStackTrace();
            throw new DAOException("Error añadiendo cliente en DAO", se);
        }
    }

	// BORRA UN CLIENTE EN MYSQL
	@Override
	public void removeItem(int id) throws DAOException {
		Cliente cliente = findById(id);
		if(cliente == null)
			 throw new DAOException("Cliente id: " + id + " does not exist to delete.");
		
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM CLIENTE WHERE IDCLIENTE=" + id;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando cliente");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando cliente en DAO", se);
        }
	}

	// ACTUALIZA UN CLIENTE EN MYSQL
	@Override
	public void updateItem(Cliente item) throws DAOException {
		try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE CLIENTE "
                    + "SET NOMBRE='" + item.getNombre() + "', "
                    +"SET APELLIDO='"+item.getApellidos()+"',"
                    +"SET DNI='"+item.getDni()+"',"
                    +"SET DIRECCION='"+item.getDireccion()+"','"
                    +"SET CORREO='"+item.getCorreo()+"',"
                    +"SET TELEFONO1="+item.getTelefono1()+","
                    +"SET TELEFONO2="+item.getTelefono2()+","
                    + "WHERE IDCLIENTE=" + item.getIdCliente();
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando cliente");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando cliente en DAO", se);
        }
	}

	@Override
	public Cliente findById(int item) throws DAOException {
		 try (Statement stmt = con.createStatement()) {
	            String query = "SELECT * FROM CLIENTE,USUARIO,ROL "
	            		+ "WHERE USUARIO=IDUSUARIO AND ROL=IDROL AND IDCLIENTE=" + item;
	            ResultSet rs = stmt.executeQuery(query);
	          
	            Cliente cliente = null;
	            while(rs.next())
	            {
	            	Rol rol = new Rol(rs.getInt(14),rs.getString(15));
	            	Usuario usuario = new Usuario(rs.getInt(10),rs.getString(11),rs.getString(12),rol);
	            	cliente = new Cliente(rs.getInt("IDCLIENTE"),
        								    rs.getString("NOMBRE"),
        									rs.getString("APELLIDO"),
				        					rs.getString("DNI"),
				        					rs.getString("DIRECCION"),
				        					rs.getString("CORREO"),
				        					rs.getInt("TELEFONO1"),
				        					rs.getInt("TELEFONO2"),
				        					usuario);
	            }
	            
	            return cliente;
	            
	        } catch (SQLException se) {
	            se.printStackTrace();
	            throw new DAOException("Error encontrando cliente en DAO", se);
	        }
	}

	@Override
	public Cliente getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void close() throws Exception {
		  try {
	            con.close();
	        } catch (SQLException se) {
	            System.out.println ("Exception closing Connection: " + se);
	        }
		
	}

}
