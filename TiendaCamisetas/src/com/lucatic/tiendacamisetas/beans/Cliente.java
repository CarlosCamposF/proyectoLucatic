package com.lucatic.tiendacamisetas.beans;

public class Cliente {
	private Usuario usuario;
	private int idCliente;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private String correo;
	private int telefono1;
	private int telefono2;
	
	public Cliente (){}
	
	public Cliente(int idCliente, String nombre, String apellidos, String dni, String direccion,String correo, int telefono1,int telefono2,Usuario usuario){
		this.idCliente=idCliente;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.nombre=nombre;
		this.dni=dni;
		this.correo =correo;
		this.telefono1=telefono1;
		this.telefono2=telefono2;
		this.usuario=usuario;
	}
	
	public Cliente(String nombre, String apellidos, String dni, String direccion,String correo, int telefono1,int telefono2,Usuario usuario){
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.nombre=nombre;
		this.dni=dni;
		this.correo = correo;
		this.telefono1=telefono1;
		this.telefono2=telefono2;
		this.usuario=usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCorreo(){
		return correo;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}

	public int getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(int telefono1) {
		this.telefono1 = telefono1;
	}
	public int getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(int telefono2) {
		this.telefono2 = telefono2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + idCliente;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + telefono1;
		result = prime * result + telefono2;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono1 != other.telefono1)
			return false;
		if (telefono2 != other.telefono2)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [usuario=" + usuario + ", idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", dni=" + dni + ", direccion=" + direccion + ", telefono1=" + telefono1 + ", telefono2="
				+ telefono2 + "]";
	}
}
