package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Detalle;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class DetallesDAOJDBCImp implements DetallesDAO{
	private Connection con = null;
	
	DetallesDAOJDBCImp() {
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
	
	public void add(Detalle det) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO detalle_compra (producto,cantidad,precio)VALUES('"+det.getProducto() + det.getCantidad()+det.getPrecio()+ "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo detalle");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo detalle en DAO", se);
        }
    }
	
	public void update(Detalle det) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE detalle_compra "
                    + "SET producto='" + det.getProducto()+ "' and SET cantidad='"+ det.getCantidad()+ "' and SET precio='"+ det.getPrecio()
                  
                    + "WHERE idDetalle_compra='" + det.getIdDetalle()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando detalle");
            }
        } catch (SQLException se) {
            throw new DAOException("Error actualizando detalle en DAO", se);
        }
    }

	public void delete(int idDetalle) throws DAOException {
        Detalle det = find(idDetalle);
        if (det == null) {
            throw new DAOException("detalle id: " + idDetalle + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM detalle_compra WHERE idDetalle_compra=" + idDetalle;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando detalle");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando detalle en DAO", se);
        }
    }

	public Detalle find(int idDetalle) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query1 = "SELECT * FROM detalle_compra WHERE idDetalle_compra=" + idDetalle;
            ResultSet rs1 = stmt.executeQuery(query1);
            String query2 = "SELECT * FROM producto, camiseta WHERE idproducto=" + rs1.getInt("producto");
            ResultSet rs2 = stmt.executeQuery(query2);
            String query3 = "SELECT * FROM GENERO WHERE idgenero=" + rs2.getInt("genero");
            ResultSet rs3 = stmt.executeQuery(query3);
            
            Genero gen = new Genero(rs1.getInt("IDGENERO"), rs1.getString("NOMBRE"));
            
            String query4 = "SELECT * FROM TALLA WHERE idtalla=" + rs2.getInt("talla");
            ResultSet rs4 = stmt.executeQuery(query4);
            
            Talla talla = new Talla(rs4.getInt("idtalla"), rs4.getString("NOMBRE"));
            
            String query5 = "SELECT * FROM COLOR WHERE idcolor=" + rs2.getInt("color");
            ResultSet rs5 = stmt.executeQuery(query5);
            
            Color color = new Color(rs5.getInt("COLOR"),rs5.getString("NOMBRE"));
            
            String query6 = "SELECT * FROM categoria WHERE idcategoria=" + rs2.getInt("categoria");
            ResultSet rs6 = stmt.executeQuery(query6);
            
            Categoria categoria = new Categoria(rs6.getInt("IDCATEGORIA"), rs6.getString("NOMBRE"));
            
            Producto p = new Camiseta(rs2.getInt("idProducto"), rs2.getString("DESCRIPCION"), categoria, gen, talla ,color,  rs2.getFloat("PRECIO"), rs2.getInt("IDCAMISETA"), rs2.getString("DIBUJO"));
           
            if (!rs1.next()) {
                return null;
            }
            return (new Detalle(rs1.getInt("IDDETALLE_COMPRA"),p,rs1.getInt("CANTIDAD"),rs1.getFloat("PRECIO")));        
            } catch (SQLException se) {
            
            throw new DAOException("Error encontrando detalle en DAO", se);
        }
    }
	
	public Detalle[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM DETALLE_COMPRA";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Detalle> detalle= new ArrayList<>();
            /*ArrayList<Categoria> categoria= new ArrayList<>();
            ArrayList<Talla> tallas= new ArrayList<>();
            ArrayList<Color> color= new ArrayList<>();
            ArrayList<Genero> gemero= new ArrayList<>();*/
            
            while (rs.next()) {
            	String query1 = "SELECT * FROM detalle_compra WHERE idDetalle_compra=" + rs.getInt("IDDETALLE_COMPRA");
                ResultSet rs1 = stmt.executeQuery(query1);
                String query2 = "SELECT * FROM producto, camiseta WHERE idproducto=" + rs1.getInt("producto");
                ResultSet rs2 = stmt.executeQuery(query2);
                String query3 = "SELECT * FROM GENERO WHERE idgenero=" + rs2.getInt("genero");
                ResultSet rs3 = stmt.executeQuery(query3);
                
                Genero gen = new Genero(rs1.getInt("IDGENERO"), rs1.getString("NOMBRE"));
                
                String query4 = "SELECT * FROM TALLA WHERE idtalla=" + rs2.getInt("talla");
                ResultSet rs4 = stmt.executeQuery(query4);
                
                Talla talla = new Talla(rs4.getInt("idtalla"), rs4.getString("NOMBRE"));
                
                String query5 = "SELECT * FROM COLOR WHERE idcolor=" + rs2.getInt("color");
                ResultSet rs5 = stmt.executeQuery(query5);
                
                Color color1 = new Color(rs5.getInt("COLOR"),rs5.getString("NOMBRE"));
                
                String query6 = "SELECT * FROM categoria WHERE idcategoria=" + rs2.getInt("categoria");
                ResultSet rs6 = stmt.executeQuery(query6);
                
                Categoria categoria1 = new Categoria(rs6.getInt("IDCATEGORIA"), rs6.getString("NOMBRE"));
                
                Producto p = new Camiseta(rs2.getInt("idProducto"), rs2.getString("DESCRIPCION"), categoria1, gen, talla ,color1,  rs2.getFloat("PRECIO"), rs2.getInt("IDCAMISETA"), rs2.getString("DIBUJO"));
               
                // Detalle e= new Detalle(rs1.getInt("IDDETALLE_COMPRA"),p,rs1.getInt("CANTIDAD"),rs1.getFloat("PRECIO"));
                detalle.add(new Detalle(rs1.getInt("IDDETALLE_COMPRA"),p,rs1.getInt("CANTIDAD"),rs1.getFloat("PRECIO")));
             
                //  detalle.add(new Detalle(rs.getInt("IDDETALLE_COMPRA"),rs.getInt("CANTIDAD"), rs.getFloat("PRECIO"), rs.getInt("FACTURA"), rs.getInt("PRODUCTO")));
            }
            return detalle.toArray(new Detalle[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo colores en DAO: " + se.getMessage(), se);
        }
    }
	
	public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println ("Exception cerrando conexión: " + se);
        }
    }
	
}
