package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.Categoria;

public interface CategoriaDAO extends AutoCloseable {

    public void add(Categoria cat) throws DAOException;

    public void update(Categoria cat) throws DAOException;

    public void delete(int idCategoria) throws DAOException;

    public Categoria find(int idCategoria) throws DAOException;

    public Categoria[] getAllTablas() throws DAOException;

}