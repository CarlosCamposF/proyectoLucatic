package com.lucatic.tiendacamisetas.model;

public class TipoPago {

	private int idTipoPago;
	private String nombreTipoPago;
	
	//CONSTRUCTORES *************************
	public TipoPago(){
		
	}
	public TipoPago(int idTipoPago, String nombreTipoPago){
		this.idTipoPago=idTipoPago;
		this.nombreTipoPago=nombreTipoPago;
	}
	public TipoPago(String nombreTipoPago){
		this.nombreTipoPago=nombreTipoPago;
	}
	
	//METODOS SETTER *********************************
	public void setTipoPago(int idTipoPago){
		this.idTipoPago=idTipoPago;
	}
	public void setNombreTipoPago(String nombreTipoPago){
		this.nombreTipoPago=nombreTipoPago;
	}
	
	//METODOS GETTER ********************************
	public int getIdTipoPago(){
		return idTipoPago;
	}
	public String getNombreTipoPago(){
		return nombreTipoPago;
	}
	@Override
	public String toString() {
		return "TipodePago ID: " + idTipoPago + "\nNombre: " + nombreTipoPago;
	}
	
	
	
}
