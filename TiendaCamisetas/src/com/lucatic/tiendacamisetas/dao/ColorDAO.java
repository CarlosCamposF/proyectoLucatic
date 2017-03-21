package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.model.Color;

public interface ColorDAO extends AutoCloseable {

    public void add(Color col) throws DAOException;

    public void update(Color col) throws DAOException;

    public void delete(int idColor) throws DAOException;

    public Color find(int idColor) throws DAOException;

    public Color[] getAllTablas() throws DAOException;

	}