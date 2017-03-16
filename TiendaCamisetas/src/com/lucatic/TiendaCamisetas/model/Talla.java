package com.lucatic.TiendaCamisetas.model;


public class Talla {

    private int idtalla;
    private String nombre;
    

    public Talla() {
    }

    public Talla(int idtalla, String nombre) {
        this.idtalla = idtalla;
        this.nombre = nombre;
        
    }

    public int getIdTalla() {
        return idtalla;
    }

    public String getNombre() {
        return nombre;
    }

    

    @Override
    public String toString() {
        return "Talla ID:   " + getIdTalla() + "\n"
                + "Nombre: " + getNombre();
    }
}