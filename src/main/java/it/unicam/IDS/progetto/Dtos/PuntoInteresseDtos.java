package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.*;

public class PuntoInteresseDtos {

    @Id
    private String nomePDI;

    private double latitudine;
    private double longitudine;

    public PuntoInteresseDtos(String nomePDI, double latitudine, double longitudine) {
        this.nomePDI = nomePDI;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }
}
