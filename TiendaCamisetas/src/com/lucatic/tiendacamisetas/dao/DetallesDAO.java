package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.beans.Detalle;


public interface DetallesDAO extends GestorDAO<Detalle>{

    

    public Detalle find(int idDetalle) throws DAOException;

    public Detalle[] getAllTablas() throws DAOException;


}