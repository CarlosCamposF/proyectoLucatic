package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.beans.Usuario;

public interface UsuarioDAO extends GestorDAO<Usuario>{
	public Usuario findByNom(String nom) throws DAOException;
}
