package com.lucatic.tiendacamisetas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class ProductoDAOImp implements ProductoDAO{
	
	private Connection con = null;
	
	public ProductoDAOImp(){
		 String driverClassName = "com.mysql.jdbc.Driver";
	        String url = "jdbc:mysql://localhost/tiendacamiseta";
	        String username = "root";
	        String password = "1111";
	        
	        try {
	            Class.forName(driverClassName);
	            con = DriverManager.getConnection(url, username, password);
	        } catch (SQLException se) {
	            System.out.println("Error obtaining connection with the database: " + se);
	            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.INFO, null, se);
	        
	            System.exit(-1);
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	@Override
	public Producto findById(int id) throws DAOException {
		Statement st = null;
		try{
			st = con.createStatement();
			String query = "SELECT * FROM producto WHERE idproducto="+id;
			ResultSet rs = st.executeQuery(query);
			if(!rs.next()){
				return null;
			}

			if(rs.getInt("categoria") == 1)
			{
				String query2 = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
						+ "WHERE  idproducto = prodid "
						+ "AND categoria = idcategoria "
						+ "AND genero = idgenero "
						+ "AND talla = idtalla "
						+ "AND color = idcolor "
						+ "AND idproducto="+id;
				
				ResultSet rs2 = st.executeQuery(query2);
				
				Categoria categoria = new Categoria(rs2.getInt(14),rs2.getString(15));
				Genero genero = new Genero(rs2.getInt(18),rs2.getString(19));
				Talla tal = new Talla(rs2.getInt(12),rs2.getString(13));
				Color col = new Color(rs2.getInt(16),rs2.getString(17));
	
				Camiseta c = new Camiseta(rs2.getInt(1),rs2.getString(2),categoria,genero,tal,col,rs2.getFloat(7),rs2.getInt(8),rs2.getString(9),rs2.getString(10));
			
				return c;
			}		
			else
			{
				return null;
			}
		
		} catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}

	@Override
	public ArrayList<Producto> findByColor(String color) throws DAOException {
		Statement st = null;
		ArrayList<Producto> productos = new ArrayList<>();
		try{
			st = con.createStatement();
			String query = "SELECT * FROM producto WHERE talla="+color;
			ResultSet rs = st.executeQuery(query);
			
			if(!rs.next())
				return null;
			else
			{
				while(rs.next())
				{
					if(rs.getInt("categoria") == 1)
					{
						String query2 = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
								+ "WHERE  idproducto = prodid "
								+ "AND categoria = idcategoria "
								+ "AND genero = idgenero "
								+ "AND talla = idtalla "
								+ "AND color = idcolor "
								+ "AND color="+color;
						
						st.executeQuery(query2);
						ResultSet rs2 = st.executeQuery(query2);
						
						Categoria categoria = new Categoria(rs2.getInt(14),rs2.getString(15));
						Genero genero = new Genero(rs2.getInt(18),rs2.getString(19));
						Talla tal = new Talla(rs2.getInt(12),rs2.getString(13));
						Color col = new Color(rs2.getInt(16),rs2.getString(17));
			
						Camiseta c = new Camiseta(rs2.getInt(1),rs2.getString(2),categoria,genero,tal,col,rs2.getFloat(7),rs2.getInt(8),rs2.getString(9),rs2.getString(10));
					
						productos.add(c);		
					}	
				
				}
			}
			
			return productos;
			
		} catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}

	@Override
	public ArrayList<Producto> findByTalla(String talla) throws DAOException {
		Statement st = null;
		ArrayList<Producto> productos = new ArrayList<>();
		try{
			st = con.createStatement();
			String query = "SELECT * FROM producto WHERE talla="+talla;
			ResultSet rs = st.executeQuery(query);
			
			if(!rs.next())
				return null;
			else
			{
				while(rs.next())
				{
					if(rs.getInt("categoria") == 1)
					{
						String query2 = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
								+ "WHERE  idproducto = prodid "
								+ "AND categoria = idcategoria "
								+ "AND genero = idgenero "
								+ "AND talla = idtalla "
								+ "AND color = idcolor "
								+ "AND talla="+talla;
						
						st.executeQuery(query2);
						ResultSet rs2 = st.executeQuery(query2);
						
						Categoria categoria = new Categoria(rs2.getInt(14),rs2.getString(15));
						Genero genero = new Genero(rs2.getInt(18),rs2.getString(19));
						Talla tal = new Talla(rs2.getInt(12),rs2.getString(13));
						Color col = new Color(rs2.getInt(16),rs2.getString(17));
			
						Camiseta c = new Camiseta(rs2.getInt(1),rs2.getString(2),categoria,genero,tal,col,rs2.getFloat(7),rs2.getInt(8),rs2.getString(9),rs2.getString(10));
					
						productos.add(c);		
					}	
				
				}
			}
			
			return productos;
			
		} catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}

	 public void addCamiseta(Camiseta item) throws DAOException{
		 Statement st = null;
		 try{
			 st = con.createStatement();
				String query = "INSERT INTO camiseta VALUES ('"+((Camiseta) item).getNombre()+"','"+((Camiseta) item).getDibujo()+"',"+item.getIdProducto()+")";
				if(st.executeUpdate(query) !=1){
					throw new DAOException("Error adding camiseta");
				}
		 }catch(SQLException se) {
	            throw new DAOException("Error adding product in DAO", se);
		 }
	 }
	
	@Override
	public void addItem(Producto item) throws DAOException {
		Statement st = null;
		try{
			st = con.createStatement();
			String query = "INSERT INTO producto VALUES ('"+ item.getDescripcion()+"',"+item.getIdCategoria()+","
					+item.getIdGenero()+","+item.getIdTalla()+","+item.getIdColor()+","+item.getPrecio()+")";
			if(st.executeUpdate(query) !=1){
				throw new DAOException("Error adding product");
			}
			
			ResultSet rs = st.executeQuery(query);
			
			if(item instanceof Camiseta)
			{
				if(rs.getInt("categoria") == 1)
				{
					addCamiseta((Camiseta)item);
				}
			}
		}catch(SQLException se) {
            throw new DAOException("Error adding product in DAO", se);
		}
		
	}

	@Override
	public void updateItem(Producto item) throws DAOException {
		Statement st = null;
		Statement st2 = null;
		
		try{
			st = con.createStatement();
			String query = "UPDATE Producto "+"SET descripcion='"+item.getDescripcion()+"', categoria="+item.getIdCategoria()+
					" genero="+item.getIdGenero()+ ", talla=" +item.getIdTalla()+ ", color="+item.getIdColor()+ ", precio="+item.getPrecio()+
					"where idproducto="+item.getIdProducto();
			st.executeQuery(query);
			
			if (st.executeUpdate(query) != 1) {
                throw new DAOException("Error updating product");
            }
			
			if(item instanceof Camiseta)
			{
				st2 = con.createStatement();
				String query2 = "UPDATE camiseta "+"SET nombre='"+((Camiseta) item).getNombre() + "', dibujo='" + ((Camiseta)item).getDibujo() +
						"', prodid="+ ((Camiseta)item).getIdProducto() + "where idcamiseta="+((Camiseta)item).getIdCamiseta();
				st2.executeQuery(query2);
			}
			
		}catch(SQLException se){
			 throw new DAOException("Error updating product in DAO", se);
		}
		
	}
	
	@Override
	public void removeItem(int item) throws DAOException {
		Statement st = null;
		Producto p;
		p = findById(item);
		if(p == null)
			 throw new DAOException("Product id: " + item + " does not exist to delete.");
		
		try{
			if(p instanceof Camiseta)
			{
				st = con.createStatement();
				String query = "DELETE FROM camiseta WHERE idcamiseta="+item;
				if(st.executeUpdate(query) !=1){
					throw new DAOException("Error adding product");
				}
			}
		}catch(SQLException se) {
            throw new DAOException("Error adding product in DAO", se);
		}
	}

	@Override
	public Producto getItem() throws DAOException {
	
		return null;
	}
	
	@Override
	public ArrayList<Producto> AllCamiseta() throws DAOException {
		Statement st = null;
		
		ArrayList<Producto> camisetas = new ArrayList<>();
		try{
			st = con.createStatement();
			
			String query = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
					+ "WHERE  idproducto = prodid "
					+ "AND categoria = idcategoria "
					+ "AND genero = idgenero "
					+ "AND talla = idtalla "
					+ "AND color = idcolor ";
			
			ResultSet rs = st.executeQuery(query);
			
			if(!rs.next())
				return null;
			else
			{
				while(rs.next())
				{
					Categoria categoria = new Categoria(rs.getInt(14),rs.getString(15));
					Genero genero = new Genero(rs.getInt(18),rs.getString(19));
					Talla tal = new Talla(rs.getInt(12),rs.getString(13));
					Color col = new Color(rs.getInt(16),rs.getString(17));
		
					Camiseta c = new Camiseta(rs.getInt(1),rs.getString(2),categoria,genero,tal,col,rs.getFloat(7),rs.getInt(8),rs.getString(9),rs.getString(10));
					camisetas.add(c);
				}
				return camisetas;
			}
			
		}catch(SQLException se){
			 throw new DAOException("Error getting all employees in DAO: " + se.getMessage(), se);
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
