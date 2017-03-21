package com.lucatic.tiendacamisetas.dao;

public class CategoriaDAOFactory {

    public CategoriaDAO createCategoriaDAO() {
        return new CategoriaDAOJDBCImpl();
    }
}