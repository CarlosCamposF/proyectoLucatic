package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.beans.Detalle;

public interface DetallesDAO extends AutoCloseable {

    public void add(Detalle det) throws DAOException;

    public void update(Detalle det) throws DAOException;

    public void delete(int idDetalle) throws DAOException;

    public Detalle find(int idDetalle) throws DAOException;

    public Detalle[] getAllTablas() throws DAOException;


}