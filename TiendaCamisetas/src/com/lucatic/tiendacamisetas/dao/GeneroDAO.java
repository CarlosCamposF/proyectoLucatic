package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.Genero;

public interface GeneroDAO extends AutoCloseable {

    public void add(Genero gen) throws DAOException;

    public void update(Genero gen) throws DAOException;

    public void delete(int idGenero) throws DAOException;

    public Genero find(int idGenero) throws DAOException;

    public Genero[] getAllTablas() throws DAOException;


}