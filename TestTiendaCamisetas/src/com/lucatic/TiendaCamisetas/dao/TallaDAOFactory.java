package com.lucatic.TiendaCamisetas.dao;

public class TallaDAOFactory {

    public TallaDAO createTallaDAO() {
        return new TallaDAOJDBCImpl();
    }
}