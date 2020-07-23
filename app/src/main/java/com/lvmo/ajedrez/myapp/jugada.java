package com.lvmo.ajedrez.myapp;

import java.util.Date;

public class jugada {

    public jugada(String jBlancasId, String invitacionId) {
        this.jBlancasId = jBlancasId;
        this.invitacionId = invitacionId;
        this.jNegrasId="";
        this.fecha = new Date();
        this.turno="blancas";
        this.abandonoId="";
        this.ganadorId="";
        this.movimientos=this.fecha.toString()+jBlancasId;
        this.pBlancas=0;
        this.pNegras=0;

    }

    private String jBlancasId;
    private String jNegrasId;
    private String invitacionId;
    private String ganadorId;
    private String abandonoId;
    private String movimientos;
    private String turno;
    private Date fecha;
    private int pBlancas;
    private int pNegras;

    public String getInvitacionId() {
        return invitacionId;
    }

    public void setInvitacionId(String invitacionId) {
        this.invitacionId = invitacionId;
    }

    public String getjBlancasId() {
        return jBlancasId;
    }

    public void setjBlancasId(String jBlancasId) {
        this.jBlancasId = jBlancasId;
    }

    public String getjNegrasId() {
        return jNegrasId;
    }

    public void setjNegrasId(String jNegrasId) {
        this.jNegrasId = jNegrasId;
    }

    public String getGanadorId() {
        return ganadorId;
    }

    public void setGanadorId(String ganadorId) {
        this.ganadorId = ganadorId;
    }

    public String getAbandonoId() {
        return abandonoId;
    }

    public void setAbandonoId(String abandonoId) {
        this.abandonoId = abandonoId;
    }

    public String getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(String movimientos) {
        this.movimientos = movimientos;
    }

    public Date getFecha() {return fecha;}

    public void setFecha(Date fecha) {this.fecha = fecha;}

    public String getTurno() {return turno;}

    public void setTurno(String turno) {this.turno = turno; }

    public int getpBlancas() { return pBlancas;    }

    public void setpBlancas(int pBlancas) {this.pBlancas = pBlancas;    }

    public int getpNegras() {return pNegras;  }

    public void setpNegras(int pNegras) {  this.pNegras = pNegras;    }

}
