package com.lucatic.TiendaCamisetas.beans;

import com.lucatic.TiendaCamisetas.model.Rol;

public class Usuario {
	
	private Rol rol;
	private int idUsuario;
	private String nombre;
	private String password;
	private String usuario;
	private String cuentaCorreo;
	
	public Usuario(){
		
	}
	public Usuario(Rol rol, int idUsuario, String nombre, String password, String usuario, String cuentaCorreo){
		this.cuentaCorreo=cuentaCorreo;
		this.idUsuario=idUsuario;
		this.nombre=nombre;
		this.password=password;
		this.rol=rol;
		this.usuario=usuario;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCuentaCorreo() {
		return cuentaCorreo;
	}
	public void setCuentaCorreo(String cuentaCorreo) {
		this.cuentaCorreo = cuentaCorreo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaCorreo == null) ? 0 : cuentaCorreo.hashCode());
		result = prime * result + idUsuario;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cuentaCorreo == null) {
			if (other.cuentaCorreo != null)
				return false;
		} else if (!cuentaCorreo.equals(other.cuentaCorreo))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol != other.rol)
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
		return "Usuario [rol=" + rol + ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", password=" + password
				+ ", usuario=" + usuario + ", cuentaCorreo=" + cuentaCorreo + "]";
	}
	
}
