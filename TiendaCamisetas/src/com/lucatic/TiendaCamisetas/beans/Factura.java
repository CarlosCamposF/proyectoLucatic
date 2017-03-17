package com.lucatic.TiendaCamisetas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lucatic.TiendaCamisetas.model.TipoPago;

public class Factura {
	
	private List <Detalle> lista= new ArrayList<Detalle>();
	private int idCliente;
	private TipoPago TipoPago;
	private Date fecha;
	private float precioTotal;
	
	public Factura(){
		
	}
	public Factura(List <Detalle> detalles, int idCliente, TipoPago tipoPago, Date fecha){
		this.lista=detalles;
		this.idCliente=idCliente;
		this.TipoPago=tipoPago;
		this.fecha=fecha;
		
		this.precioTotal=calcularTotal(detalles);
		}
	public float calcularTotal(List <Detalle> detalles){
		float total=0;
		for (Detalle a: detalles){
			total=+a.getPrecio();
		}
		
		return total;
	}
	public List<Detalle> getLista() {
		return lista;
	}
	public void setLista(List<Detalle> lista) {
		this.lista = lista;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public TipoPago getTipoPago() {
		return TipoPago;
	}
	public void setTipoPago(TipoPago tipoPago) {
		TipoPago = tipoPago;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TipoPago == null) ? 0 : TipoPago.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idCliente;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + Float.floatToIntBits(precioTotal);
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
		Factura other = (Factura) obj;
		if (TipoPago != other.TipoPago)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (Float.floatToIntBits(precioTotal) != Float.floatToIntBits(other.precioTotal))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Factura [lista=" + lista + ", idCliente=" + idCliente + ", TipoPago=" + TipoPago + ", fecha=" + fecha
				+ ", precioTotal=" + precioTotal + "]";
	}
	
}
