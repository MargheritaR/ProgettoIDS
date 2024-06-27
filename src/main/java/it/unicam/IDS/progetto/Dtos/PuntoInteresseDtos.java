package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.*;

public class PuntoInteresseDtos {

    @Id
    private String nomePDI;

    private double asseX;
    private double asseY;

    public PuntoInteresseDtos(String nomePDI, double asseX, double asseY) {
        this.nomePDI = nomePDI;
        this.asseX = asseX;
        this.asseY = asseY;
    }

    public String getNomePDI() {
        return nomePDI;
    }

    public void setNomePDI(String nomePDI) {
        this.nomePDI = nomePDI;
    }

    public double getAsseX() {
        return asseX;
    }

    public void setAsseX(double asseX) {
        this.asseX = asseX;
    }

    public double getAsseY() {
        return asseY;
    }

    public void setAsseY(double asseY) {
        this.asseY = asseY;
    }
}
