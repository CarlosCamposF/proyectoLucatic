package com.lucatic.tiendacamisetas.dao;

public class UsuarioDAOFactory {

    public UsuarioDAO createUsuarioDAO() {
        return new UsuarioDAOJDBCImpl();
    }
}