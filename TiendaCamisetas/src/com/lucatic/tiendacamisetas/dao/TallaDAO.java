package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.Talla;

public interface TallaDAO extends AutoCloseable {

    public void add(Talla tall) throws DAOException;

    public void update(Talla tall) throws DAOException;

    public void delete(int idtalla) throws DAOException;

    public Talla findById(int idtalla) throws DAOException;

    public Talla[] getAllTallas() throws DAOException;
}