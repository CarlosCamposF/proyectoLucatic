package com.lucatic.tiendacamisetas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Usuario;
import com.lucatic.tiendacamisetas.model.Rol;

public class UsuarioDAOImpl implements UsuarioDAO{

	private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    UsuarioDAOImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(GeneroDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneroDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	
	
	
	@Override
	public void addItem(Usuario item) throws DAOException {
		try(Statement stmt=con.createStatement()){
			String query = "INSERT INTO USUARIO (nombre,password,correo,rol) values('"+
		item.getNombreUsuario()+"','"+item.getPassword()+"','"+item.getCuentaCorreo()+"','"+item.getRol()+"')";
			if (stmt.executeUpdate(query)!=1){
				throw new DAOException("Error añadiendo Usuario");
			}
		}catch(SQLException se){
			throw new DAOException("Error añadiendo Usuario en DAO",se);
		}
		
	}

	@Override
	public void removeItem(int item) throws DAOException {
		Usuario usu = find(item);
		if(usu==null){
			throw new DAOException("Usuario id: "+item+"no existe.");
		}
		try(Statement stmt=con.createStatement()){
			String query = "DELETE FROM USUARIO WHERE IDUSUARIO="+item;
		if (stmt.executeUpdate(query)!=1){
			throw new DAOException("Error borrando usuario");
		}
		}catch (SQLException se){
			throw new DAOException("Error borrando Usuario en DAO",se);
		}
		
		
	}
//update usuario set nombre='pepe',correo='pepe@gmail.com' where idusuario='11'
	@Override
	public void updateItem(Usuario item) throws DAOException {
		try(Statement stmt=con.createStatement()){
			String query = "UPDATE USUARIO"
			+"SET NOMBRE='"+item.getNombreUsuario()+"',"
			+"SET PASSWORD='"+item.getPassword()+"',"
			+"SET CORREO='"+item.getCuentaCorreo()+"',"
			+"SET ROL='"+item.getRol()+"' WHERE IDUSUARIO='"+item.getIdUsuario()+"'";
			if (stmt.executeUpdate(query)!=1){
			}
			}catch(SQLException se){
		throw new DAOException("Error modificando usuario en DAO",se);
		}
	}

	@Override
	public Usuario getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Usuario find(int idUsuario) throws DAOException {
		try(Statement stmt=con.createStatement()){
			String query="SELECT * FROM USUARIO,ROL WHERE ROL=IDROL AND IDUSUARIO="+idUsuario;
			ResultSet rs=stmt.executeQuery(query);
			if(!rs.next()){
				return null;
			}
			Rol rol=new Rol(rs.getInt(6),rs.getString(7));
			
			return (new Usuario(rs.getInt("IDUSUARIO"),rs.getString("NOMBRE"),rs.getString("PASSWORD"),rs.getString("CORREO"),rol));
		}catch(SQLException se){
			throw new DAOException("Error encontrado Usuario en DAO",se);
			
		}
	}
		
		
		
		

	
	

}
