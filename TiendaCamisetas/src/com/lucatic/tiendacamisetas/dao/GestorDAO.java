package com.lucatic.tiendacamisetas.dao;

public interface GestorDAO<T> {
	public void addItem(T item) throws DAOException;
	public void removeItem(int item) throws DAOException;
	public void updateItem(T item) throws DAOException;
	public T getItem() throws DAOException;
}
