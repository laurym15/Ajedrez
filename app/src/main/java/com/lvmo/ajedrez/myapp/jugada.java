package com.lvmo.ajedrez.myapp;

public class jugada {

    public jugada(String jBlancasId, String invitacionId) {
        this.jBlancasId = jBlancasId;
        this.invitacionId = invitacionId;
    }

    private String jBlancasId;
    private String jNegrasId;
    private String invitacionId;
    private String ganadorId;
    private String abandonoId;
    private String movimientos;

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

}
