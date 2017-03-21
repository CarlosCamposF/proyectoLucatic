package com.lucatic.tiendacamisetas.dao;

public class ColorDAOFactory {

    public ColorDAO createColorDAO() {
        return new ColorDAOJDBCImpl();
    }
}