package com.lucatic.tiendacamisetas.beans;

import com.lucatic.tiendacamisetas.model.*;

public class Camiseta extends Producto{
	private int idCamiseta;
	private String dibujo;
	
	
	
	public Camiseta(){
		}
	
	public Camiseta(int idProducto, String descripcion, Categoria idCategoria, Genero idGenero, Talla idTalla, Color idColor, float precio, int idCamiseta, String dibujo){
	super();
	super.setDescripcion(descripcion);
	super.setIdCategoria(idCategoria);
	super.setIdColor(idColor);
	super.setIdGenero(idGenero);
	super.setIdProducto(idProducto);
	super.setIdTalla(idTalla);
	super.setPrecio(precio);
	this.idCamiseta=idCamiseta;
	this.dibujo=dibujo;
		}

	public int getIdCamiseta() {
		return idCamiseta;
	}

	public void setIdCamiseta(int idCamiseta) {
		this.idCamiseta = idCamiseta;
	}

	public String getDibujo() {
		return dibujo;
	}

	public void setDibujo(String dibujo) {
		this.dibujo = dibujo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dibujo == null) ? 0 : dibujo.hashCode());
		result = prime * result + idCamiseta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camiseta other = (Camiseta) obj;
		if (dibujo == null) {
			if (other.dibujo != null)
				return false;
		} else if (!dibujo.equals(other.dibujo))
			return false;
		if (idCamiseta != other.idCamiseta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Camiseta [idCamiseta=" + idCamiseta + ", dibujo=" + dibujo + "]";
	}
	
}
