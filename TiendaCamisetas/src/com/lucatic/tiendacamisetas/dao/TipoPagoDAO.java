package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.TipoPago;

public interface TipoPagoDAO extends AutoCloseable {

    public void add(TipoPago tip) throws DAOException;

    public void update(TipoPago tip) throws DAOException;

    public void delete(int idTipoPago) throws DAOException;

    public TipoPago find(int idTipoPago) throws DAOException;

    public TipoPago[] getAllTablas() throws DAOException;

	
}