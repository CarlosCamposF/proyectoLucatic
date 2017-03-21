package com.lucatic.tiendacamisetas.dao;

public class RolDAOFactory {

    public RolDAO createRolDAO() {
        return new RolDAOJDBCImpl();
    }
}