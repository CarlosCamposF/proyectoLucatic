package com.lucatic.tiendacamisetas.dao;

public class TallaDAOFactory {

    public TallaDAO createTallaDAO() {
        return new TallaDAOJDBCImpl();
    }
}