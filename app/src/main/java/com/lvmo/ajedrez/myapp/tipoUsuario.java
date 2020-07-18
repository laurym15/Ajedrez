package com.lvmo.ajedrez.myapp;

public class tipoUsuario {
    public tipoUsuario(String usuarioId) {
        this.nombre = "juagador"+usuarioId;
        this.UsuarioId = usuarioId;
        this.puntosTotales = 0;
        this.onLine="on";
    }

    private String nombre;
    private String UsuarioId;
    private String onLine;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        UsuarioId = usuarioId;
    }

    public String getOnLine() {
        return onLine;
    }

    public void setOnLine(String onLine) {
        this.onLine = onLine;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    private int puntosTotales;

}
