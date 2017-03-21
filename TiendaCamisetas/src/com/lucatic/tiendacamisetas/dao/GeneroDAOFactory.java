package com.lucatic.tiendacamisetas.dao;

public class GeneroDAOFactory {

    public GeneroDAO createGeneroDAO() {
        return new GeneroDAOJDBCImpl();
    }
}