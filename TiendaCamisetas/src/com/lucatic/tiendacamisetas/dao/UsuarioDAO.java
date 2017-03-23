package com.lucatic.tiendacamisetas.dao;

import com.lucatic.tiendacamisetas.beans.Usuario;

public interface UsuarioDAO extends GestorDAO<Usuario>{

	public Usuario find(int idUsuario) throws DAOException;
	
}
