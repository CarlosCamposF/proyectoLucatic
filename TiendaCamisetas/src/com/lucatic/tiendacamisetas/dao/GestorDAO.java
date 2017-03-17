package com.lucatic.tiendacamisetas.dao;

public interface GestorDAO<T> {
	public void addItem(T item) throws DAOException;
	public void removeItem(T item) throws DAOException;
	public void updateItem(T item) throws DAOException;
	public T getItem(T item) throws DAOException;
}
