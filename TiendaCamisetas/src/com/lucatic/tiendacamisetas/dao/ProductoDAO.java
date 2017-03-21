package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.beans.Producto;

public interface ProductoDAO extends GestorDAO<Producto>{
	 public Producto findById(int id) throws DAOException;
	 public Producto findByColor(String color) throws DAOException;
	 public Producto findByTalla(String talla) throws DAOException;
}
