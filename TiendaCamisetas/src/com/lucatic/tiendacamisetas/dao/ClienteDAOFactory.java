package com.lucatic.tiendacamisetas.dao;

public class ClienteDAOFactory {
	public ClienteDAO createClienteDAO(){
		return new ClienteDAOJDBCImpl();
	}
}
