package com.lvmo.ajedrez.myapp;

public class tipoUsuario {

    private int points;
    private String name;
    private String UsuarioId;
    private String onLine;
    private String usuarioVerificado;

    public tipoUsuario() {
    }

    public tipoUsuario(int points, String name, String usuarioId, String onLine, String usuarioVerificado) {
        this.points = points;
        this.name = name;
        UsuarioId = usuarioId;
        this.onLine = onLine;
        this.usuarioVerificado = usuarioVerificado;
    }

    public int getPoints() {        return points; }

    public void setPoints(int points) {    this.points = points;    }

    public String getName() {        return name;    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsuarioVerificado() { return usuarioVerificado; }

    public void setUsuarioVerificado(String usuarioVerificado) {        this.usuarioVerificado = usuarioVerificado; }
}
