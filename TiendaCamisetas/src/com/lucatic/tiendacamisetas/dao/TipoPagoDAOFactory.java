package com.lucatic.tiendacamisetas.dao;

public class TipoPagoDAOFactory {

    public TipoPagoDAO createTipoPagoDAO() {
        return new TipoPagoDAOJDBCImpl();
    }
}