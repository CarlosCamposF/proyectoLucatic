package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.Rol;

public interface RolDAO extends AutoCloseable {

    public void add(Rol rol) throws DAOException;

    public void update(Rol rol) throws DAOException;

    public void delete(int idRol) throws DAOException;

    public Rol find(int idRol) throws DAOException;

    public Rol[] getAllTablas() throws DAOException;

}