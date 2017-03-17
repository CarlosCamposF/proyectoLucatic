package com.lucatic.TiendaCamisetas.beans;

public class Detalle {

	private int idDetalle;
	private int idProducto;
	private int cantidad;
	private float precio;
	
	public Detalle(){
		
	}
	public Detalle(int idDetalle, int idProducto, int cantidad, float precio){
		this.idDetalle=idDetalle;
		this.idProducto=idProducto;
		this.cantidad=cantidad;
		this.precio=precio;
		}
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
		result = prime * result + idDetalle;
		result = prime * result + idProducto;
		result = prime * result + Float.floatToIntBits(precio);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalle other = (Detalle) obj;
		if (cantidad != other.cantidad)
			return false;
		if (idDetalle != other.idDetalle)
			return false;
		if (idProducto != other.idProducto)
			return false;
		if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Detalle [idDetalle=" + idDetalle + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", precio="
				+ precio + "]";
	}
}
