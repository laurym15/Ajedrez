package com.lvmo.ajedrez.myapp;

public class tipoUsuario {
    public tipoUsuario(String usuarioId) {
        this.name = "juagador"+usuarioId;
        this.UsuarioId = usuarioId;
        this.points = 0;
        this.onLine="on";
        this.usuarioVerificado="no";
    }

    private String name;
    private String UsuarioId;
    private String onLine;
    private String usuarioVerificado;
    private int points;

    public String getNombre() { return name;    }

    public void setNombre(String nombre) {
        this.name = nombre;
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
        return points;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.points = puntosTotales;
    }

    public String getUsuarioVerificado() { return usuarioVerificado; }

    public void setUsuarioVerificado(String usuarioVerificado) {        this.usuarioVerificado = usuarioVerificado; }
}
