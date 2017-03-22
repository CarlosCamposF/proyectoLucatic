package com.lucatic.tiendacamisetas.dao;

public class DetallesDAOFactory {

	public DetallesDAO createGeneroDAO(){
		return new DetallesDAOJDBCImp();
	}
}
