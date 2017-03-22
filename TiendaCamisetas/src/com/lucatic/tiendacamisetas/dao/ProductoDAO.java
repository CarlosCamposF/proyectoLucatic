package com.lucatic.tiendacamisetas.dao;

import java.util.ArrayList;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Producto;

public interface ProductoDAO extends GestorDAO<Producto>{
	 public Producto findById(int id) throws DAOException;
	 public ArrayList<Producto> findByColor(String color) throws DAOException;
	 public ArrayList<Producto> findByTalla(String talla) throws DAOException;
	 public void addCamiseta(Camiseta c) throws DAOException;
	 public ArrayList<Producto> AllCamiseta() throws DAOException;
}
